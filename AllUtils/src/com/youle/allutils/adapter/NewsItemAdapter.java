package com.youle.allutils.adapter;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.youle.allutils.R;
import com.youle.allutils.entity.NewsItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//“™Œ≈   ≈‰∆˜
public class NewsItemAdapter extends SimpleBaseAdapter<NewsItem> {

	public NewsItemAdapter(Context c, List<NewsItem> datas) {
		super(c, datas);

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
		return convertView;
	}

	private class EntityHolder {
		@ViewInject(R.id.item_tv_title)
		TextView item_tv_title;
	}

}
