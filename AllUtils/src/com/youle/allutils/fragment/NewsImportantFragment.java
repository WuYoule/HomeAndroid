package com.youle.allutils.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.Templates;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnScroll;
import com.lidroid.xutils.view.annotation.event.OnScrollStateChanged;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youle.allutils.R;
import com.youle.allutils.adapter.NewsHeadAdapter;
import com.youle.allutils.adapter.NewsItemAdapter;
import com.youle.allutils.application.LocalApplication;
import com.youle.allutils.entity.NewsItem;
import com.youle.allutils.view.ToastMaker;

//��Ѷ-Ҫ��
public class NewsImportantFragment extends BaseFragment {

	@ViewInject(R.id.news_important_lv)
	private ListView lv;

	// ����Դ
	private List<NewsItem> data = new ArrayList<NewsItem>();

	// ������
	private NewsItemAdapter adapter;

	// ���ز���
	private LinearLayout loading_llyt;

	// �Ƿ������һ��
	private boolean isLast = false;

	// �Ƿ��и�������
	private boolean isMore = true;
	// �Ƿ����ڼ�������
	private boolean isLoading = false;

	private int pageIndex = 0;
	private int pageSize = 20;

	// ������ͼ
	private FrameLayout news_head_view;
	// viewpager
	private ViewPager news_head_vp;
	
	
	private TextView news_head_tv;
	
	
	private TextView news_head_tv1;
	
	
	private TextView news_head_tv2;
	
	
	private TextView news_head_tv3;
	
	
	private TextView news_head_tv4;
	
	
	private TextView news_head_tv5;
	
	//�Զ��ֲ���ʱ��
	private ScheduledExecutorService scheduledExecutorService;
	//��ǰ�ֲ�ͼƬ������
	private int currentItem=0;
	
	//ָʾ��
	private List<TextView> textViewList=new ArrayList<TextView>();
	

	// �����ֲ�����Դ
	private List<NewsItem> headList = new ArrayList<NewsItem>();
	// �����ֲ�������
	private NewsHeadAdapter headAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_news_important;
	}
	

	@Override
	protected void initParams() {

		// ���صײ����಼��
		loading_llyt = (LinearLayout) getLayoutInflater(null).inflate(
				R.layout.listview_loading_view, null);

		// �����ֲ�ͼ ����
		news_head_view = (FrameLayout) getLayoutInflater(null).inflate(
				R.layout.news_head_view, null);
		// ��ȡ�ֲ�ͼ viewpager
		news_head_vp = (ViewPager) news_head_view
				.findViewById(R.id.news_head_vp);
		
		news_head_tv=(TextView) news_head_view.findViewById(R.id.news_head_tv);
		news_head_tv1=(TextView) news_head_view.findViewById(R.id.news_head_tv1);
		news_head_tv2=(TextView) news_head_view.findViewById(R.id.news_head_tv2);
		news_head_tv3=(TextView) news_head_view.findViewById(R.id.news_head_tv3);
		news_head_tv4=(TextView) news_head_view.findViewById(R.id.news_head_tv4);
		news_head_tv5=(TextView) news_head_view.findViewById(R.id.news_head_tv5);

		// ��ʱ��ģ�����ݵ�
		for (int i = 0; i < 2; i++) {
			NewsItem item1 = new NewsItem();
			item1.cover_image = "http://192.168.56.1/youle/logo" + i + ".png";
			item1.image_list="[{\"title\":\""+"ͼƬ����1"+"\",\"url\":\""+ "http://192.168.56.1/youle/logo0.png"+"\"},{\"title\":\""+"ͼƬ����2"+"\",\"url\":\""+ "http://192.168.56.1/youle/logo1.png"+"\"}]";
//			item1.image_list.title="ͼƬ����"+i;
//			item1.image_list.url="http://192.168.56.1/youle/logo" + i + ".png";
			headList.add(item1);
		}
		news_head_tv3.setVisibility(View.GONE);
		news_head_tv4.setVisibility(View.GONE);
		news_head_tv5.setVisibility(View.GONE);
		
		//news_head_tv.setText("����1");
		//ָʾ��
		textViewList.add(news_head_tv1);
		textViewList.add(news_head_tv2);
		textViewList.add(news_head_tv3);
		textViewList.add(news_head_tv4);
		textViewList.add(news_head_tv5);
		// ��ʼ���ֲ�ͼ
		headAdapter = new NewsHeadAdapter(context, headList);
		// ���ֲ�ͼviewpager����adapter
		news_head_vp.setAdapter(headAdapter);
		news_head_vp.setOnPageChangeListener(new MyNewsPageChangeListener());
		news_head_vp.setCurrentItem(headList.size()*1000);
		
		// ��ʱ��ģ�����ݵ�
		for (int i = 0; i < 20; i++) {
			NewsItem item = new NewsItem();
			item.content = "����һ������" + i;
			data.add(item);
		}
		

		// ��ʼ�������б�
		adapter = new NewsItemAdapter(context, data);
		// �������setAdapter����ǰ��
		lv.addFooterView(loading_llyt);
		lv.addHeaderView(news_head_view);

		// �������б�����adpater
		lv.setAdapter(adapter);

		// �����ֲ�ͼ����
		// MyLoadHeadData();
	}

	//�ֲ�ͼviewpager��������
	private class MyNewsPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			currentItem=arg0;
			for (int i = 0; i < headList.size(); i++) {
				if (i==arg0%headList.size()) {
					textViewList.get(i).setBackgroundColor(context.getResources().getColor(R.color.lunbo_selected));
				}
				else {
					textViewList.get(i).setBackgroundColor(context.getResources().getColor(R.color.lunbo_default));
				}
			}
			
		}} 
	// ����ͷ������
	private void MyLoadHeadData() {
		LocalApplication.getInstance().httpUtils.send(HttpMethod.GET, "",
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String list = JSONObject.parseObject(arg0.result)
								.getString("list");
						List<NewsItem> tmp = JSONObject.parseArray(list,
								NewsItem.class);
						if (tmp.size() > 0) {
							headList.addAll(tmp);
							headAdapter.MyRefreshDatas(headList);
							news_head_vp.setCurrentItem(headList.size()*1000);
							news_head_tv.setText(headList.get(0).title);
							
							//ָʾ���Ƽ����5�� ����5��Ҫ ����
							if (tmp.size()<5) {
								for (int i = 0; i < 5-tmp.size(); i++) {
									textViewList.get(textViewList.size()-1-i).setVisibility(View.GONE);
								}
								
							}
						}
					}
				});

	}

	// �����б�����
	private void MyLoadListData() {
		isLoading = true;
		pageIndex++;
		RequestParams params = new RequestParams();
		params.addBodyParameter("pageIndex", pageIndex + "");
		params.addBodyParameter("pageSize", pageSize + "");

		LocalApplication.getInstance().httpUtils.send(HttpMethod.POST, "",
				params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// �õ��˵��������ؽ��ȿؼ� progresswheel
						// pw.stopSpinning();
						// pw.setVisibility(View.GONE);������xml�ļ��м�������ؼ�
						lv.removeFooterView(loading_llyt);
						ToastMaker.showShortToast("����ʧ�ܣ�������������ԣ�");

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String list = JSONObject.parseObject(arg0.result)
								.getString("list");
						List<NewsItem> tmp = JSONObject.parseArray(list,
								NewsItem.class);
						// pw.stopSpinning();
						// pw.setVisibility(View.GONG);
						lv.setVisibility(View.VISIBLE);
						if (tmp.size() > 0) {
							data.addAll(tmp);
							adapter.refreshDatas(data);
							if (pageIndex == 1) {
								if (tmp.size() < pageSize) {
									// �Ƴ��ײ����ز���
									lv.removeFooterView(loading_llyt);
								}

							}
						} else {
							isMore = false;
							lv.removeFooterView(loading_llyt);
							ToastMaker.showShortToast("�����Ѿ�ȫ����������");
						}

					}
				});

	}

	@OnScrollStateChanged(R.id.news_important_lv)
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// �������ײ� ���ҹ����Ѿ�ֹͣ��
		if (isLast && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {

			if (!isLoading && isMore) {
				MyLoadListData();
				// new AsyncTask<Void, Void, Void>() {
				//
				// @Override
				// protected Void doInBackground(Void... params) {
				//
				// try {
				// Thread.sleep(3000);
				//
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// data.addAll(data);
				// return null;
				// }
				//
				// @Override
				// protected void onPostExecute(Void result) {
				// super.onPostExecute(result);
				// isLoading = true;
				// isMore = false;
				// adapter.notifyDataSetChanged();
				//
				// }
				//
				// }.execute();
			}
			// else {
			// lv.removeFooterView(loading_llyt);
			// ToastMaker.showShortToast("����ȫ���������ˣ�");
			// }

			isLast = false;
		}
	}

	@OnScroll(R.id.news_important_lv)
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// �ǲ��ǹ������ײ�
		if (firstVisibleItem + visibleItemCount == totalItemCount
				&& totalItemCount > 0) {
			isLast = true;
		}

	}
	
	@Override
	public void onStart() {
		super.onStart();
		scheduledExecutorService=Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 5, 5, TimeUnit.SECONDS);
	}
	@Override
	public void onStop() {
		super.onStop();
		if (scheduledExecutorService!=null) {
			scheduledExecutorService.shutdown();
		}
	}
	private class ScrollTask implements Runnable{

		@Override
		public void run() {
		   synchronized (news_head_vp) {
			   currentItem++;
			 //ͨ��handler�л�ͼƬ
			   handler.sendEmptyMessage(1);
		}
		}
		
	}
	private Handler handler=new Handler(){
		 public void handleMessage(android.os.Message msg) {
			 switch (msg.what) {
			 //�л�ͼƬ
			case 1:
				if (headList.size()>0) {
					news_head_vp.setCurrentItem(currentItem);
				}
				break;

			default:
				break;
			}
			 
		 };
		
	};

	@Override
	protected void initParams(View view) {
	}

}
