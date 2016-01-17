package com.wqy.view;

import com.wqy.dianping.R;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


//绘制英文字母
public class SiderBar extends View {

	//new对象时调用
	public SiderBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//xml文件创建控件对象时调用
	public SiderBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	
	}
	//26个字母
	public static String[] sidebar={"热门","A","B","C","D","E","F","G","H","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	private Paint paint;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint=new Paint();
		paint.setColor(Color.GRAY);//设置画笔颜色
		paint.setTypeface(Typeface.DEFAULT_BOLD);//字体粗体
		paint.setTextSize(40);//画出来字体大小
		//获取自定义控件的宽高
		int height=getHeight();
		int width=getWidth();
		//设定每个字母在控件中所占的高度
		int each_height=height/sidebar.length;
		
		//把每个字母绘制出来
		for (int i = 0; i < sidebar.length; i++) {
			float x=width/2-paint.measureText(sidebar[i])/2;//字母相对他的父控件的x轴的偏移量
			float y=(1+i)*each_height;
			
			canvas.drawText(sidebar[i], x, y, paint);
		}
	}
	
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	private int choose;
	//定义监听事件
	public interface OnTouchingLetterChangedListener{
		public void OnTouchingLetterChanged(String s);
	}
	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener){
		this.onTouchingLetterChangedListener=onTouchingLetterChangedListener;
		
	}
	
	//分发对应的touch事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
	
		final int action=event.getAction();//获取对应的动作
		final float y=event.getY();//点击的Y坐标
		final OnTouchingLetterChangedListener listener=onTouchingLetterChangedListener;
	    final int c=(int)(y/getHeight()*sidebar.length);
	    switch (action) {
		case MotionEvent.ACTION_UP://手指抬起
			setBackgroundResource(android.R.color.transparent);
			invalidate();
			break;

		default:
			setBackgroundResource(R.color.normal_bg_color);
			
			if (c>0&&c<sidebar.length) {
				listener.OnTouchingLetterChanged(sidebar[c]);
			}
			choose=c;
			invalidate();
			
			break;
		}
		
		
		
		return true;
	}
	 
	
}
