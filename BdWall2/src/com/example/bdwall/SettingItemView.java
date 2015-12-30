package com.example.bdwall;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingItemView extends LinearLayout {
	TextView leftTv;
	TextView rightTv;
	
	
	
	
	

	public SettingItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();

	}

	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.setting_view_item, this);
		findView(view);

	}

	private void findView(View view) {
		leftTv = (TextView) view.findViewById(R.id.mylefttv);
		rightTv = (TextView) view.findViewById(R.id.myrighttv);

	}

	public TextView getLeftText() {

		return leftTv;
	}

	public TextView getRightText() {

		return rightTv;
	}
	
	public void setLeftText(String txt){
		leftTv.setText(txt);
	}
	public void setRightText(String txt){
		rightTv.setText(txt);
	}
	
	public void setRightBitmap( int resId){
		rightTv.setBackgroundResource(resId);
	}

}
