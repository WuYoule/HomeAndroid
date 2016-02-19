package com.youle.allutils.fragment;

//import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView.OnItemClickListener;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.youle.allutils.R;
import com.youle.allutils.activity.MainActivity;
import com.youle.allutils.activity.MainActivity.FragmentOnTouchListener;
//import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.adapter.SecondPinnedSectionedAdapter;
import com.youle.allutils.view.SideBar;
import com.youle.allutils.view.ToastMaker;

public class FindBrandFragment extends BaseFragment implements
		FragmentOnTouchListener {

	@ViewInject(R.id.pinnedListView)
	PinnedHeaderListView find_brand_lv;

	@ViewInject(R.id.find_brand_sb)
	SideBar find_brand_sb;

	@ViewInject(R.id.find_brand_llyt_content)
	LinearLayout find_brand_llyt_content;

	@ViewInject(R.id.find_cover_lv)
	PinnedHeaderListView find_cover_lv;

	// 是否显示二级菜单
	private boolean isShow = false;

	private WindowManager windowManager;

	// 提示对话框
	@ViewInject(R.id.xianshi_tv)
	private TextView dialogText;

	// 手势监听器
	private GestureDetector gestureDetector;

	@Override
	protected int getLayoutId() {

		return R.layout.fragment_find_brand_main;
	}

	@Override
	protected void initParams() {

	}

	@Override
	protected void initParams(View view) {
		PinnedHeaderListView listView = (PinnedHeaderListView) view
				.findViewById(R.id.pinnedListView);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int section, int position, long id) {
				Log.i("wu", section + "**" + position);
				if (!isShow) {
					// 显示二级菜单
					showContent();
				}
				// 加载二级菜单数据
			}

			@Override
			public void onSectionClick(AdapterView<?> adapterView, View view,
					int section, long id) {
			}
		});

		PinnedHeaderListView secondlistView = (PinnedHeaderListView) view
				.findViewById(R.id.find_cover_lv);
		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// LinearLayout header1 = (LinearLayout)
		// inflator.inflate(R.layout.list_item, null);
		// ((TextView) header1.findViewById(R.id.textItem)).setText("HEADER 1");
		// LinearLayout header2 = (LinearLayout)
		// inflator.inflate(R.layout.list_item, null);
		// ((TextView) header2.findViewById(R.id.textItem)).setText("HEADER 2");
		// LinearLayout footer = (LinearLayout)
		// inflator.inflate(R.layout.list_item, null);
		// ((TextView) footer.findViewById(R.id.textItem)).setText("FOOTER");
		// listView.addHeaderView(header1);
		// listView.addHeaderView(header2);
		// listView.addFooterView(footer);
		PinnedSectionedAdapter sectionedAdapter = new PinnedSectionedAdapter();
		listView.setAdapter(sectionedAdapter);

		SecondPinnedSectionedAdapter secondAdapter = new SecondPinnedSectionedAdapter();

		secondlistView.setAdapter(secondAdapter);

		// 初始化提示对话框
		// dialogText=(TextView)
		// LayoutInflater.from(context).inflate(R.layout.fragment_find_brand_ic_index,
		// null);
		// dialogText.setVisibility(View.VISIBLE);
		//
		// 初始化窗口管理器
		// windowManager=(WindowManager)
		// context.getSystemService(context.WINDOW_SERVICE);
		// WindowManager.LayoutParams lp=new
		// WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//
		// //将对话框添加到窗口中
		// windowManager.addView(dialogText, lp);

		// 设置侧边栏信息
		find_brand_sb.setTextView(dialogText);
		find_brand_sb.setListView(find_brand_lv);

		((MainActivity) getActivity()).registerFragmentOnTouchListener(this);
		gestureDetector = new GestureDetector(context, onGestureListener);

	}

	// 显示二级菜单
	public void showContent() {
		find_brand_sb.setVisibility(View.GONE);
		find_brand_llyt_content.setVisibility(View.VISIBLE);
		find_brand_llyt_content.startAnimation(AnimationUtils.loadAnimation(
				context, R.anim.in_from_right));
		isShow = true;
	}

	// 隐藏二级菜单
	public void closeContent() {
		find_brand_sb.setVisibility(View.VISIBLE);
		find_brand_llyt_content.startAnimation(AnimationUtils.loadAnimation(
				context, R.anim.out_from_right));
		find_brand_llyt_content.setVisibility(View.INVISIBLE);
		isShow = false;
	}

	@Override
	public boolean onTouch(MotionEvent ev) {
		return gestureDetector.onTouchEvent(ev);
	}

	// 手势滑动监听器
	private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {

		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.i("？？？", "滑动了滑动了");

			// 手机滑动失去焦点
			find_cover_lv.setPressed(false);
			find_cover_lv.setFocusable(false);
			find_cover_lv.setFocusableInTouchMode(false);
			return super.onScroll(e1, e2, distanceX, distanceY);

		};

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float x = e2.getX() - e1.getX();
			float y = Math.abs(e2.getY() - e1.getY());
			Log.i("%%%%", isShow + "");
			// 向右滑动到一定距离时隐藏内容
			if (isShow) {
				if (x > 500) {
					// if (y<0) {
					closeContent();
					// }

				}
			}

			return true;
		};

	};

}
