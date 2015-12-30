package com.example.bdwall;

import java.security.PublicKey;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class LoadView extends ScrollView{

	 public static final String LOAD="load";
	

	public LoadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	//只要手指在屏幕上滑動他就會不停的調用這個方法
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
	
		check();
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public interface ICallBack{
		public void click(String bottomortop);
		
	}
	ICallBack callBack=null;
	
	public void setIcallback(ICallBack callBack){
		this.callBack=callBack;
	}
	
	//上拉 下拉 监听
	private void check() {
		if (getChildAt(0)!=null&& getChildAt(0).getMeasuredHeight()<=getScaleY()+getHeight()) {
			//这就是 拉到底部了
			
			if (PullLoadRefreshView.getBottomOrTopTag()) {
				return;
			}
			callBack.click(LOAD);//回调到下一个页面
		}
		
	}

}
