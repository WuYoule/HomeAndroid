package com.youle.allutils.view;

import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SideBar extends View {

	// 字母表
	private char[] alphabet;
	// 列表
	private PinnedHeaderListView listView;
	private SectionIndexer sectionIndexer;
	// 提示对话框
	private TextView dialogText;
	public SideBar(Context context) {
		super(context);
		init();
	}
	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	// 初始化字母表
	private void init() {
		alphabet = new char[] {
		'#', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	}
	public void setListView(PinnedHeaderListView listView){
		this.listView=listView;
		sectionIndexer=(SectionIndexer) listView.getAdapter();	
	}
	public void setTextView(TextView dialogText){
		this.dialogText=dialogText;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

}
