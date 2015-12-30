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

	public static final String LOAD = "load";// ���� ���ײ���

	LoadView loadView;
	MyGridView gridView;
	LinearLayout linearLayout;

	static boolean bottomOrTop=false;//��ʾ���Ǜ]�� �ײ� �� 픲�
	
	public PullLoadRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	// ��ʼ��
	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.pull_load_refresh, this);

		findView(view);

		setCallBack();

	}

	// ���F LoadView �Ľӿ�
	private void setCallBack() {
		loadView.setIcallback(new MyCallBack());

	}

	private class MyCallBack implements LoadView.ICallBack {

		@Override
		public void click(String bottomortop) {
			if (bottomortop.equals(LOAD)) { // �f����߅�ѽ����_�ײ���
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
				 //�@ʾ�ײ�view
				setBottomShow();
				 //�O�� bottomortoptag
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

		public void load();// ���Vactivity��Ҫ���d������

		public void refresh();// ��Ҫˢ������
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
	
	//�@ȡ��ǰ�@��B�Ƿ������M��
	public static boolean getBottomOrTopTag(){
		return bottomOrTop;
		
	}
	
	//�������d���֮���{�õķ���
	public void dataFinsh() {
		handler.sendEmptyMessage(101);
	}
	
	//����loadview
	public LoadView getLoadView(){
		return loadView;
	}
	//���� gridview
	public MyGridView getGridView(){
		return gridView;
		
	}

}
