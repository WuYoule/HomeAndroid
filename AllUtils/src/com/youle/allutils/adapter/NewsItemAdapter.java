package com.youle.allutils.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;
import com.youle.allutils.R;
import com.youle.allutils.entity.NewsItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//“™Œ≈   ≈‰∆˜
public class NewsItemAdapter extends SimpleBaseAdapter<NewsItem> {
	private Context context;
	public NewsItemAdapter(Context c, List<NewsItem> datas) {
		super(c, datas);
		this.context=c;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EntityHolder entityHolder = null;
		if (convertView == null) {
			entityHolder = new EntityHolder();
			convertView = layoutInflater.inflate(R.layout.news_item, null);
			ViewUtils.inject(entityHolder, convertView);
			convertView.setTag(entityHolder);
		} else {
			entityHolder = (EntityHolder) convertView.getTag();
		}
		entityHolder.item_tv_title.setText(datas.get(position).content);
		setImage(position,context, entityHolder.item_iv_img);
		return convertView;
	}

	private class EntityHolder {
		@ViewInject(R.id.item_tv_title)
		TextView item_tv_title;

		@ViewInject(R.id.item_iv_img)
		ImageView item_iv_img;

	}

	private void setImage(int code,Context context,ImageView imgview) {
		switch (code) {
		case 0:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.new_iv1).into(imgview);
			break;
		case 1:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.new_iv2).into(imgview);
			break;
		case 2:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv3).into(imgview);
			break;
		case 3:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv4).into(imgview);
			break;
		case 4:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv5).into(imgview);
			break;
		case 5:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv6).into(imgview);
			break;
		case 6:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv7).into(imgview);
			break;
		case 7:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv8).into(imgview);
			break;
		case 8:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv9).into(imgview);
			break;
		case 9:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv10).into(imgview);
			break;
		case 10:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv11).into(imgview);
			break;
		case 11:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv12).into(imgview);
			break;
		case 12:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv13).into(imgview);
			break;
		case 13:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv14).into(imgview);
			break;
		case 14:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv15).into(imgview);
			break;
		case 15:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv16).into(imgview);
			break;
		case 16:
			Picasso.with(context).load(datas.get(code).cover_image)
			.placeholder(R.drawable.news_iv17).into(imgview);
			break;

		default:
			break;
		}

	}

}
