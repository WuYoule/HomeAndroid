package com.wqy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;


//����Ӣ����ĸ
public class SiderBar extends View {

	//new����ʱ����
	public SiderBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//xml�ļ������ؼ�����ʱ����
	public SiderBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	
	}
	//26����ĸ
	public static String[] sidebar={"����","A","B","C","D","E","F","G","H","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	private Paint paint;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint=new Paint();
		paint.setColor(Color.GRAY);//���û�����ɫ
		paint.setTypeface(Typeface.DEFAULT_BOLD);//�������
		paint.setTextSize(20);//�����������С
		//��ȡ�Զ���ؼ��Ŀ��
		int height=getHeight();
		int width=getWidth();
		//�趨ÿ����ĸ�ڿؼ�����ռ�ĸ߶�
		int each_height=height/sidebar.length;
		
		//��ÿ����ĸ���Ƴ���
		for (int i = 0; i < sidebar.length; i++) {
			float x=width/2-paint.measureText(sidebar[i])/2;//��ĸ������ĸ��ؼ���x���ƫ����
			float y=(1+i)*each_height;
			
			canvas.drawText(sidebar[i], x, y, paint);
		}
	}

	 
	
}
