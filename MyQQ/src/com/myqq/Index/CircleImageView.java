package com.myqq.Index;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * * 自定义的圆形ImageView，可以直接当组件在布局中使用。
 * 
 * @author caizhiming
 * */
public class CircleImageView extends ImageView {
	private Paint paint;

	public CircleImageView(Context context) {
		this(context, null);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();

	}

	/**
	 * * 绘制圆形图片
	 * 
	 * @author caizhiming
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			Bitmap b = getCircleBitmap(bitmap, 14);
			final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
			final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();
			canvas.drawBitmap(b, rectSrc, rectDest, paint);

		} else {
			super.onDraw(canvas);
		}
	}

	/**
	 * * 获取圆形图片方法
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return Bitmap
	 * @author caizhiming
	 */
	private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		
//		 //圆形图片宽高  
//        int width = bitmap.getWidth();  
//        int height = bitmap.getHeight();  
//        //正方形的边长  
//        int r = 0;  
//        //取最短边做边长  
//        if(width > height) {  
//            r = height;  
//        } else {  
//            r = width;  
//        }  
//		
//		Bitmap output = Bitmap.createBitmap(r,
//				r, Config.ARGB_8888);
//		Canvas canvas = new Canvas(output);
//		final int color = 0xff424242;
//		final Rect rect = new Rect(0, 0, r, r);
//		 //设置边缘光滑，去掉锯齿  
//		paint.setAntiAlias(true);
//		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(color);
//		int x = bitmap.getWidth();
//
//		canvas.drawCircle(r / 2, r / 2, r / 2, paint);
//		 //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉  
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, rect, rect, paint);
//		return output;
		
		
		//圆形图片宽高  
        int width = bitmap.getWidth();  
        int height = bitmap.getHeight();  
        //正方形的边长  
        int r = 0;  
        //取最短边做边长  
        if(width > height) {  
            r = height;  
        } else {  
            r = width;  
        }  
        //构建一个bitmap  
        Bitmap backgroundBmp = Bitmap.createBitmap(width,  
                 height, Config.ARGB_8888);  
        //new一个Canvas，在backgroundBmp上画图  
        Canvas canvas = new Canvas(backgroundBmp);  
        Paint paint = new Paint();  
        //设置边缘光滑，去掉锯齿  
        paint.setAntiAlias(true);  
        //宽高相等，即正方形  
        RectF rect = new RectF(0, 0, r, r);  
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，  
        //且都等于r/2时，画出来的圆角矩形就是圆形  
        canvas.drawCircle(r/2, r/2, r/2, paint);  
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        //canvas将bitmap画在backgroundBmp上  
        canvas.drawBitmap(bitmap, null, rect, paint);  
        //返回已经绘画好的backgroundBmp  
        return backgroundBmp;  

	}
}
