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

//资讯-要闻
public class NewsImportantFragment extends BaseFragment {

	@ViewInject(R.id.news_important_lv)
	private ListView lv;

	// 数据源
	private List<NewsItem> data = new ArrayList<NewsItem>();

	// 适配器
	private NewsItemAdapter adapter;

	// 加载布局
	private LinearLayout loading_llyt;

	// 是否是最后一个
	private boolean isLast = false;

	// 是否还有更多数据
	private boolean isMore = true;
	// 是否正在加载数据
	private boolean isLoading = false;

	private int pageIndex = 0;
	private int pageSize = 20;

	// 新闻组图
	private FrameLayout news_head_view;
	// viewpager
	private ViewPager news_head_vp;
	
	
	private TextView news_head_tv;
	
	
	private TextView news_head_tv1;
	
	
	private TextView news_head_tv2;
	
	
	private TextView news_head_tv3;
	
	
	private TextView news_head_tv4;
	
	
	private TextView news_head_tv5;
	
	//自动轮播定时器
	private ScheduledExecutorService scheduledExecutorService;
	//当前轮播图片的索引
	private int currentItem=0;
	
	//指示器
	private List<TextView> textViewList=new ArrayList<TextView>();
	

	// 新闻轮播数据源
	private List<NewsItem> headList = new ArrayList<NewsItem>();
	// 新闻轮播适配器
	private NewsHeadAdapter headAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_news_important;
	}
	

	@Override
	protected void initParams() {

		// 加载底部更多布局
		loading_llyt = (LinearLayout) getLayoutInflater(null).inflate(
				R.layout.listview_loading_view, null);

		// 加载轮播图 布局
		news_head_view = (FrameLayout) getLayoutInflater(null).inflate(
				R.layout.news_head_view, null);
		// 获取轮播图 viewpager
		news_head_vp = (ViewPager) news_head_view
				.findViewById(R.id.news_head_vp);
		
		news_head_tv=(TextView) news_head_view.findViewById(R.id.news_head_tv);
		news_head_tv1=(TextView) news_head_view.findViewById(R.id.news_head_tv1);
		news_head_tv2=(TextView) news_head_view.findViewById(R.id.news_head_tv2);
		news_head_tv3=(TextView) news_head_view.findViewById(R.id.news_head_tv3);
		news_head_tv4=(TextView) news_head_view.findViewById(R.id.news_head_tv4);
		news_head_tv5=(TextView) news_head_view.findViewById(R.id.news_head_tv5);

		// 暂时的模拟数据的
		for (int i = 0; i < 2; i++) {
			NewsItem item1 = new NewsItem();
			item1.cover_image = "http://192.168.56.1/youle/logo" + i + ".png";
			item1.image_list="[{\"title\":\""+"图片标题1"+"\",\"url\":\""+ "http://192.168.56.1/youle/logo0.png"+"\"},{\"title\":\""+"图片标题2"+"\",\"url\":\""+ "http://192.168.56.1/youle/logo1.png"+"\"}]";
//			item1.image_list.title="图片标题"+i;
//			item1.image_list.url="http://192.168.56.1/youle/logo" + i + ".png";
			headList.add(item1);
		}
		news_head_tv3.setVisibility(View.GONE);
		news_head_tv4.setVisibility(View.GONE);
		news_head_tv5.setVisibility(View.GONE);
		
		//news_head_tv.setText("标题1");
		//指示器
		textViewList.add(news_head_tv1);
		textViewList.add(news_head_tv2);
		textViewList.add(news_head_tv3);
		textViewList.add(news_head_tv4);
		textViewList.add(news_head_tv5);
		// 初始化轮播图
		headAdapter = new NewsHeadAdapter(context, headList);
		// 给轮播图viewpager设置adapter
		news_head_vp.setAdapter(headAdapter);
		news_head_vp.setOnPageChangeListener(new MyNewsPageChangeListener());
		news_head_vp.setCurrentItem(headList.size()*1000);
		
		// 暂时的模拟数据的
		for (int i = 0; i < 20; i++) {
			NewsItem item = new NewsItem();
			item.content = "这是一条新闻" + i;
			data.add(item);
		}
		

		// 初始化新闻列表
		adapter = new NewsItemAdapter(context, data);
		// 必须放在setAdapter（）前面
		lv.addFooterView(loading_llyt);
		lv.addHeaderView(news_head_view);

		// 给新闻列表设置adpater
		lv.setAdapter(adapter);

		// 加载轮播图数据
		// MyLoadHeadData();
	}

	//轮播图viewpager滑动监听
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
	// 加载头部数据
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
							
							//指示器推荐最多5个 低于5个要 隐藏
							if (tmp.size()<5) {
								for (int i = 0; i < 5-tmp.size(); i++) {
									textViewList.get(textViewList.size()-1-i).setVisibility(View.GONE);
								}
								
							}
						}
					}
				});

	}

	// 加载列表数据
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
						// 用到了第三方加载进度控件 progresswheel
						// pw.stopSpinning();
						// pw.setVisibility(View.GONE);还用在xml文件中加上这个控件
						lv.removeFooterView(loading_llyt);
						ToastMaker.showShortToast("请求失败，请检查往后后重试！");

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
									// 移除底部加载布局
									lv.removeFooterView(loading_llyt);
								}

							}
						} else {
							isMore = false;
							lv.removeFooterView(loading_llyt);
							ToastMaker.showShortToast("数据已经全部加载完了");
						}

					}
				});

	}

	@OnScrollStateChanged(R.id.news_important_lv)
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// 滚动到底部 并且滚动已经停止了
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
			// ToastMaker.showShortToast("数据全部加载完了！");
			// }

			isLast = false;
		}
	}

	@OnScroll(R.id.news_important_lv)
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// 是不是滚动到底部
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
			 //通过handler切换图片
			   handler.sendEmptyMessage(1);
		}
		}
		
	}
	private Handler handler=new Handler(){
		 public void handleMessage(android.os.Message msg) {
			 switch (msg.what) {
			 //切换图片
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
