package com.youle.allutils.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.youle.allutils.R;
import com.youle.allutils.view.circleindicator.widget.CircleIndicator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends BaseActivity {

	@ViewInject(R.id.guide_vp)
	ViewPager guide_vp;
	
	@ViewInject(R.id.guide_vp_indicator)
	CircleIndicator guide_vp_indicator;
	
	//数据源
	private List<View> dataList=new ArrayList<>();
	
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private RelativeLayout layout4;
	
	private FrameLayout layouts;
	
	private Button guide_btn_start;
	
	
	private ImageView guide_iv1;
	private ImageView guide_iv2;
	private ImageView guide_iv3;
	private ImageView guide_iv4;
	
	private Bitmap bitmap1;
	private Bitmap bitmap2;
	private Bitmap bitmap3;
	private Bitmap bitmap4;
	
	
	public static void startActivity(Context context) {
		Intent intent = new Intent(context, GuideActivity.class);
		context.startActivity(intent);

	}

	@Override
	protected void initParams() {
           layout1=(LinearLayout) getLayoutInflater().inflate(R.layout.guide_item1, null);
           layout2=(LinearLayout) getLayoutInflater().inflate(R.layout.guide_item2, null);
           layout3=(LinearLayout) getLayoutInflater().inflate(R.layout.guide_item3, null);
           layout4= (RelativeLayout) getLayoutInflater().inflate(R.layout.guide_item4, null);
           
           
           guide_iv1=(ImageView) layout1.findViewById(R.id.guide_iv1);
           guide_iv2=(ImageView) layout1.findViewById(R.id.guide_iv2);
           guide_iv3=(ImageView) layout1.findViewById(R.id.guide_iv3);
           guide_iv4=(ImageView) layout1.findViewById(R.id.guide_iv4);
           
           
//           bitmap1=readBitmap(this,R.drawable.anim1);
//           bitmap2=readBitmap(this,R.drawable.anim2);
//           bitmap3=readBitmap(this,R.drawable.anim3);
//           bitmap4=readBitmap(this,R.drawable.anim4);
//           
//           guide_iv1.setImageBitmap(bitmap1);
//           guide_iv2.setImageBitmap(bitmap2);
//           guide_iv3.setImageBitmap(bitmap3);
//           guide_iv4.setImageBitmap(bitmap4);
           
            //立即体验
           guide_btn_start=(Button) layout4.findViewById(R.id.guide_btn_start);
           guide_btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
                  MainActivity.startActivity(GuideActivity.this);
				  finish();
			}
		});
           
           dataList.add(layout1);
           dataList.add(layout2);
           dataList.add(layout3);
           dataList.add(layout4);
           guide_vp.setAdapter(new GuideViewPagerAdapter());
           guide_vp_indicator.setViewPager(guide_vp);
	}

	//以最省内存的方式读取本地资源图片
	private Bitmap readBitmap(Context context, int resid) {
          BitmapFactory.Options opt=new BitmapFactory.Options();
          opt.inPreferredConfig=Bitmap.Config.RGB_565;
          opt.inPurgeable=true;
          opt.inInputShareable=true;
          //获取资源图片
          InputStream is=context.getResources().openRawResource(resid);
          
		return BitmapFactory.decodeStream(is,null,opt);
	}

	@Override
	protected void onDestroy() {
	if (bitmap1!=null&&bitmap1.isRecycled()) {
		bitmap1.recycle();
	}
	if (bitmap2!=null&&bitmap2.isRecycled()) {
		bitmap1.recycle();
	}
	if (bitmap3!=null&&bitmap3.isRecycled()) {
		bitmap3.recycle();
	}
	if (bitmap4!=null&&bitmap4.isRecycled()) {
		bitmap4.recycle();
	}
		super.onDestroy();
	}
	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.guide_main;
	}
	class GuideViewPagerAdapter extends PagerAdapter{

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
		      ((ViewPager)container).addView(dataList.get(position),0);
			return dataList.get(position);
		}
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        	// TODO Auto-generated method stub
        	((ViewPager)container).removeView(dataList.get(position));
        }
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}}
	
}
