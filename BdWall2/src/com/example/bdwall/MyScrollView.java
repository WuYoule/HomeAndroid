package com.example.bdwall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

	float xStartLocation;
	float yStartLocation;
	float xDistance;
	float yDistance;
	//事件分发
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return super.dispatchKeyEvent(event);
	}

	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:
			xStartLocation=ev.getX();
			yStartLocation=ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float xcurrentLocation=ev.getX();
			float ycurrentLocation=ev.getY();
			xDistance+=Math.abs(xcurrentLocation-xStartLocation);
			yDistance+=Math.abs(ycurrentLocation-yStartLocation);
			xStartLocation=xcurrentLocation;
			yStartLocation=ycurrentLocation;
			if(xDistance>yDistance){
				return false;
			}
			break;
		
		}
		return super.onInterceptTouchEvent(ev);
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

}
