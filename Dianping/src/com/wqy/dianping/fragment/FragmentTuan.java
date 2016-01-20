package com.wqy.dianping.fragment;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.squareup.picasso.Picasso;
import com.wqy.consts.CONSTS;
import com.wqy.dianping.GoodsDetailActivity;
import com.wqy.dianping.R;
import com.wqy.entity.Categroy;
import com.wqy.entity.Goods;
import com.wqy.entity.ResponseObject;

import android.content.Intent;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentTuan extends Fragment {

	@ViewInject(R.id.index_listGoods)
	private PullToRefreshListView listviewGoods;
	protected List<Goods> goods;

	private MyAdapter adapter;

	// ����Ʒ�б�����ʱ����ʾ����
	@OnItemClick(R.id.index_listGoods)
	public void OnItemClick(AdapterView<?> parent, View view, int position,
			long id) {
           Intent intent=new Intent(getActivity(),GoodsDetailActivity.class);
           Bundle bundle = new Bundle();
           bundle.putSerializable("goods", goods.get(position-1));
           intent.putExtras(bundle);
           
           startActivity(intent);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.tuan_index, null);
		ViewUtils.inject(this, view);

		listviewGoods
				.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);// ֧����������
		listviewGoods.setScrollingWhileRefreshingEnabled(true);// ������ʱ�򲻼�������
		listviewGoods.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// ����ˢ�� y<0
				loadDatas(listviewGoods.getScrollY() < 0);

			}
		});

		// �״�����ҳ��Ҫ�Զ���������
		new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {

				listviewGoods.setRefreshing();
				return true;
			}
		}).sendEmptyMessageDelayed(0, 300);

		return view;

	}

	private int page, size = 10, count;// ��ʼ������

	// ��������
	public void loadDatas(final boolean refresh) {
		Log.i("TAG000", refresh + "");
		if (refresh) {
			page = 1;
			Log.i("TAG111", page + "");
		} else {
			page++;
			Log.i("TAG222", page + "");
		}

		RequestParams params = new RequestParams();
		params.addQueryStringParameter("page", page + "");
		params.addQueryStringParameter("size", size + "");
		// ʹ��xutils
		new HttpUtils().send(HttpMethod.GET, CONSTS.Good_Data_URI, params,
				new RequestCallBack<String>() {

					// ʧ����ִ�еķ���
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						listviewGoods.onRefreshComplete();// ֹͣˢ��
						Toast.makeText(getActivity(), arg1, Toast.LENGTH_SHORT)
								.show();

					}

					// �ɹ�ʱִ�еķ���
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						listviewGoods.onRefreshComplete();// ֹͣˢ��
						Gson gson = new Gson();
						Type listType = new TypeToken<ResponseObject<List<Goods>>>() {
						}.getType();
						ResponseObject<List<Goods>> object = gson.fromJson(
								arg0.result, listType);
						page = object.getPage();
						size = object.getSize();
						count = object.getCount();
						// goods = object.getDatas();
						// Log.i("TAG", page + "--" + size);
						// adapter = new MyAdapter();
						// listviewGoods.setAdapter(adapter);
						// �ж�ʱ������ˢ�»�����������
						if (refresh) {// ����ˢ��
							goods = object.getDatas();
							adapter = new MyAdapter();
							listviewGoods.setAdapter(adapter);
						} else {// ���ظ���
						// if (page == 1) {
						// goods = object.getDatas();
						// adapter = new MyAdapter();
						// listviewGoods.setAdapter(adapter);
						// } else {
							Log.i("TAG333", object.getDatas().size() + "");
							goods.addAll(object.getDatas());
							adapter.notifyDataSetChanged();
							// }

						}

						if (count == page) {
							listviewGoods
									.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START);// ֻ��ˢ��
						}
					}

				});
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return goods.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyHolder holder;
			if (convertView == null) {
				holder = new MyHolder();
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.tuan_goods_list_item, null);
				ViewUtils.inject(holder, convertView);
				convertView.setTag(holder);
			} else {
				holder = (MyHolder) convertView.getTag();
			}
			Goods good = goods.get(position);
			holder.title.setText(good.getTitle());
			holder.titleContent.setText(good.getSortTitle());
			holder.price.setText("��" + good.getPrice());
			holder.count.setText(good.getBought() + "��");

			StringBuffer sb = new StringBuffer("��" + good.getValue());
			// ����л���
			SpannableString spannableString = new SpannableString(sb);
			spannableString.setSpan(new StrikethroughSpan(), 0, sb.length(),
					Spanned.SPAN_INCLUSIVE_INCLUSIVE);

			holder.value.setText(spannableString);

			// ʹ��picasso ����ͼƬ����oom�Լ�ͼƬ��λ
			Picasso.with(parent.getContext()).load(good.getImgUrl())
					.placeholder(R.drawable.default_pic).into(holder.image);

			return convertView;
		}

	}

	public class MyHolder {
		@ViewInject(R.id.index_gl_item_image)
		public ImageView image;
		@ViewInject(R.id.index_gl_item_title)
		public TextView title;
		@ViewInject(R.id.index_gl_item_titlecontent)
		public TextView titleContent;
		@ViewInject(R.id.index_gl_item_price)
		public TextView price;
		@ViewInject(R.id.index_gl_item_value)
		public TextView value;
		// @ViewInject(R.id.index_gl_item_area)
		// public TextView area;
		@ViewInject(R.id.index_gl_item_count)
		public TextView count;

	}
}
