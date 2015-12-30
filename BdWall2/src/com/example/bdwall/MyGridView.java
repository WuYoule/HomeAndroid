package com.example.bdwall;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	//È·¶¨³ß´ç
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int exPectHeight=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, exPectHeight);
	}

}
