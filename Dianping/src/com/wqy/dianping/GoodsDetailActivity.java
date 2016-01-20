package com.wqy.dianping;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.squareup.picasso.Picasso;
import com.wqy.entity.Goods;
import com.wqy.entity.Shop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsDetailActivity extends Activity{
	
	@ViewInject(R.id.goods_img)
	private ImageView goods_image;
	
	@ViewInject(R.id.goods_title)
	private TextView goods_title;
	
	@ViewInject(R.id.goods_desc)
	private TextView goods_desc;
	
	@ViewInject(R.id.shop_title)
	private TextView shop_title;
	
	@ViewInject(R.id.shop_phone)
	private TextView shop_phone;
	
	@ViewInject(R.id.goods_price)
	private TextView goods_price;
	
	@ViewInject(R.id.goods_old_price)
	private TextView goods_old_price;
	
	@ViewInject(R.id.tv_more_details_web_view)
	private WebView tv_more_details_web_view;
	
	@ViewInject(R.id.wv_warn)
	private WebView wv_warn;
	
	
	private Goods goods;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuan_good_detail);
		ViewUtils.inject(this);
		//文字中划线效果
		goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		//让网页自适应屏幕
		WebSettings webSettings=tv_more_details_web_view.getSettings();
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		
		WebSettings webSettings02=wv_warn.getSettings();
		webSettings02.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		
		Intent intent=getIntent();
		if (intent!=null) {
			goods=(Goods) intent.getSerializableExtra("goods");
		}
		if (goods!=null) {
			//更新页面上所有内容
			updateTitleImage();
			updateGoodsInfo();
			updateShopInfo();
			updateMoreDetails();
			
		}
		
	}
	
	@OnClick({R.id.shop_call,R.id.goods_detail_goback})
	public void OnClick(View view){
		switch (view.getId()) {
		case R.id.shop_call:
			Intent intent=new Intent(Intent.ACTION_DIAL);
			 intent.setData(Uri.parse("tel:"+goods.getShop().getShop_tel()));
			 startActivity(intent);
			break;
		case R.id.goods_detail_goback:
			finish();

		default:
			break;
		}
	}
	
	private void updateMoreDetails() {
		
		String[] data=htmlsub(goods.getDetail());
		tv_more_details_web_view.loadDataWithBaseURL("", data[0], "text/html", "UTF-8", "");
		wv_warn.loadDataWithBaseURL("", data[1], "text/html", "UTF-8", "");
	}
	public String[] htmlsub(String html) {
		char[] str=html.toCharArray();
		int len=str.length;
		int n=0;
		String[] data=new String[3];
		int oneIndex=0;
		int secIndex=1;
		int thiIndex=2;
		for (int i = 0; i < len; i++) {
			if (str[i]=='【') {
				n++;
				if (n==1) {
					oneIndex=i;
				}
				if (n==2) {
					secIndex=i;
				}
				if (n==3) {
					thiIndex=i;
				}
			}
		}
		if (oneIndex>0&&secIndex>1&&thiIndex>2) {
			data[0]=html.substring(oneIndex+6,secIndex);
			data[1]=html.substring(secIndex+6,thiIndex);
			data[2]=html.substring(thiIndex+6,html.length()-6);
		}
		return data;
		
	}
	//商铺信息
	private void updateShopInfo() {
		Shop shop=goods.getShop();
		shop_title.setText(shop.getShop_name());
		shop_phone.setText(shop.getShop_tel());
		
		
	}
	//商品信息
	private void updateGoodsInfo() {
		goods_title.setText(goods.getSortTitle());
		goods_desc.setText(goods.getTitle());
		goods_price.setText("￥"+goods.getPrice());
		goods_old_price.setText("￥"+goods.getValue());
	}
	//更新商品的标题图片
	private void updateTitleImage() {
		
		Picasso.with(this).load(goods.getImgUrl()).placeholder(R.drawable.default_pic).into(goods_image);
		
	}

}
