package com.example.bdwall;

import android.R.bool;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class PullLoadRefreshView extends LinearLayout {

	public static final String LOAD = "load";// 上拉 到底部了

	LoadView loadView;
	MyGridView gridView;
	LinearLayout linearLayout;

	static boolean bottomOrTop=false;//表示的是沒有 底部 或 頂部
	
	public PullLoadRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	// 初始化
	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.pull_load_refresh, this);

		findView(view);

		setCallBack();

	}

	// 實現 LoadView 的接口
	private void setCallBack() {
		loadView.setIcallback(new MyCallBack());

	}

	private class MyCallBack implements LoadView.ICallBack {

		@Override
		public void click(String bottomortop) {
			if (bottomortop.equals(LOAD)) { // 說明那邊已經到達底部了
                   pullCallBack.load();
                   
                   handler.sendEmptyMessage(100);
                   
			}
			else{
				pullCallBack.refresh();
			}

		}
	}
	
	public void setBottomShow(){
		
		linearLayout.setVisibility(View.VISIBLE);
       
		
		
	}
	public void setBottomGone() {
		linearLayout.setVisibility(View.GONE);
	}
	
	Handler handler=new Handler(){
	      public void  handleMessage(Message message) {
			switch (message.what) {
			case 100:
				 //顯示底部view
				setBottomShow();
				 //設置 bottomortoptag
				bottomOrTop=true;
				break;
			case 101:
				setBottomGone();
				bottomOrTop=false;

			default:
				break;
			}
		}
		
	};

	public interface IPullCallBack {

		public void load();// 告訴activity需要加載數據了

		public void refresh();// 需要刷新數據了
	}

	IPullCallBack pullCallBack = null;

	public void setIPullClick(IPullCallBack iCallBack) {
		this.pullCallBack = iCallBack;
	}

	private void findView(View view) {
		loadView = (LoadView) view.findViewById(R.id.plr_loadview);
		gridView = (MyGridView) view.findViewById(R.id.plr_gridview);
		linearLayout = (LinearLayout) view.findViewById(R.id.plr_linearlayout);

	}
	
	//獲取當前這狀態是否正在進行
	public static boolean getBottomOrTopTag(){
		return bottomOrTop;
		
	}
	
	//數據加載完成之後調用的方法
	public void dataFinsh() {
		handler.sendEmptyMessage(101);
	}
	
	//返回loadview
	public LoadView getLoadView(){
		return loadView;
	}
	//返回 gridview
	public MyGridView getGridView(){
		return gridView;
		
	}

}
