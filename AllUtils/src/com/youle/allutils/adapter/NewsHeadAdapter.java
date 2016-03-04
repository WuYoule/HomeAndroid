package com.youle.allutils.adapter;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.youle.allutils.R;
import com.youle.allutils.activity.NewsPictureActivity;
import com.youle.allutils.entity.NewsItem;
import com.youle.allutils.view.ToastMaker;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class NewsHeadAdapter  extends PagerAdapter{
	
	private List<NewsItem> datas;
	private Context context;
	private LayoutInflater layoutInflater;
	public NewsHeadAdapter(Context context,List<NewsItem> datas){
		this.datas=datas;
		this.context=context;
	    layoutInflater=LayoutInflater.from(context);
		
	}
	//刷新适配器
	public void MyRefreshDatas(List<NewsItem> datas){
		this.datas=datas;
		this.notifyDataSetChanged();
	}
	

	@Override
	public int getCount() {
		//return datas.size();
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
              ( (ViewPager)container).removeView((View) object);
 
	}
	
	@Override
	public Object instantiateItem(View container, final int position) {
			View layout=layoutInflater.inflate(R.layout.news_head_item, null);
			ImageView viewpager_item_img=(ImageView) layout.findViewById(R.id.viewpager_head_item_img);
			// 使用picasso 避免图片出现oom以及图片错位
			if (datas.get(position%datas.size()).id==0) {
				Picasso.with(context).load(datas.get(position%datas.size()).cover_image)
				.placeholder(R.drawable.img1).into(viewpager_item_img);

			}
			if (datas.get(position%datas.size()).id==1) {
				Picasso.with(context).load(datas.get(position%datas.size()).cover_image)
				.placeholder(R.drawable.img2).into(viewpager_item_img);

			}
			
			viewpager_item_img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (datas.get(position%datas.size()).image_list!=null) {
						NewsPictureActivity.startActivity(context,datas.get(position%datas.size()).image_list);
					}
					else {
						ToastMaker.showShortToast("新闻详情");
					}
				}
			});
			
			
			((ViewPager)container).addView(layout);
			return layout;
	}
	
	

}
