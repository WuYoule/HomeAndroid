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
 * * �Զ����Բ��ImageView������ֱ�ӵ�����ڲ�����ʹ�á�
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
	 * * ����Բ��ͼƬ
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
	 * * ��ȡԲ��ͼƬ����
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return Bitmap
	 * @author caizhiming
	 */
	private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		
//		 //Բ��ͼƬ���  
//        int width = bitmap.getWidth();  
//        int height = bitmap.getHeight();  
//        //�����εı߳�  
//        int r = 0;  
//        //ȡ��̱����߳�  
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
//		 //���ñ�Ե�⻬��ȥ�����  
//		paint.setAntiAlias(true);
//		canvas.drawARGB(0, 0, 0, 0);
//		paint.setColor(color);
//		int x = bitmap.getWidth();
//
//		canvas.drawCircle(r / 2, r / 2, r / 2, paint);
//		 //���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��  
//		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, rect, rect, paint);
//		return output;
		
		
		//Բ��ͼƬ���  
        int width = bitmap.getWidth();  
        int height = bitmap.getHeight();  
        //�����εı߳�  
        int r = 0;  
        //ȡ��̱����߳�  
        if(width > height) {  
            r = height;  
        } else {  
            r = width;  
        }  
        //����һ��bitmap  
        Bitmap backgroundBmp = Bitmap.createBitmap(width,  
                 height, Config.ARGB_8888);  
        //newһ��Canvas����backgroundBmp�ϻ�ͼ  
        Canvas canvas = new Canvas(backgroundBmp);  
        Paint paint = new Paint();  
        //���ñ�Ե�⻬��ȥ�����  
        paint.setAntiAlias(true);  
        //�����ȣ���������  
        RectF rect = new RectF(0, 0, r, r);  
        //ͨ���ƶ���rect��һ��Բ�Ǿ��Σ���Բ��X�᷽��İ뾶����Y�᷽��İ뾶ʱ��  
        //�Ҷ�����r/2ʱ����������Բ�Ǿ��ξ���Բ��  
        canvas.drawCircle(r/2, r/2, r/2, paint);  
        //���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        //canvas��bitmap����backgroundBmp��  
        canvas.drawBitmap(bitmap, null, rect, paint);  
        //�����Ѿ��滭�õ�backgroundBmp  
        return backgroundBmp;  

	}
}
