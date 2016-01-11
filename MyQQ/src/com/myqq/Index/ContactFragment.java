package com.myqq.Index;

import com.example.myqq.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ContactFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
	View view=	inflater.inflate(R.layout.tab_item_contact, container, false);
//	ImageView img = (ImageView) view.findViewById(R.id.contact_img);
//	
//	Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.load);
//	Bitmap newb = Bitmap.createBitmap( 300, 300, Config.ARGB_8888 );
//	
//	//Log.i("bitmap",newb.toString().length()+"" );
//	
//	Bitmap touxiang=toRoundBitmap(newb);
//	//Log.i("touxiang",touxiang.toString() );
//	img.setImageBitmap(touxiang);
	
	return view;
	
	}
	
	 public Bitmap toRoundBitmap(Bitmap bitmap) {  
	        //Բ��ͼƬ���  
	        int width = bitmap.getWidth();  
	        int height = bitmap.getHeight();  
	        Log.i("bitmap",width+"--"+height );
	        
	        //�����εı߳�  
	        int r = 0;  
	        //ȡ��̱����߳�  
	        if(width > height) {  
	            r = height;  
	        } else {  
	            r = width;  
	        }  
	        //����һ��bitmap  
	        Bitmap backgroundBmp = Bitmap.createBitmap(width/2,  
	                 height/2, Config.ARGB_8888);  
	        //newһ��Canvas����backgroundBmp�ϻ�ͼ  
	        Canvas canvas = new Canvas(backgroundBmp);  
	        Paint paint = new Paint();  
	        //���ñ�Ե�⻬��ȥ�����  
	        paint.setAntiAlias(true);  
	        //�����ȣ���������  
	        RectF rect = new RectF(0, 0, r, r);  
	        //ͨ���ƶ���rect��һ��Բ�Ǿ��Σ���Բ��X�᷽��İ뾶����Y�᷽��İ뾶ʱ��  
	        //�Ҷ�����r/2ʱ����������Բ�Ǿ��ξ���Բ��  
	        canvas.drawRoundRect(rect, r/2, r/2, paint);  
	        //���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��  
	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	        //canvas��bitmap����backgroundBmp��  
	        canvas.drawBitmap(bitmap, null, rect, paint);  
	        //�����Ѿ��滭�õ�backgroundBmp  
	        return backgroundBmp;  
	    }  

}
