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
	
	
	//ֻҪ��ָ����Ļ�ϻ������͕���ͣ���{���@������
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
	
	//���� ���� ����
	private void check() {
		if (getChildAt(0)!=null&& getChildAt(0).getMeasuredHeight()<=getScaleY()+getHeight()) {
			//����� �����ײ���
			
			if (PullLoadRefreshView.getBottomOrTopTag()) {
				return;
			}
			callBack.click(LOAD);//�ص�����һ��ҳ��
		}
		
	}

}
