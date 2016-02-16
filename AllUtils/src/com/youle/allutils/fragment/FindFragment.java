package com.youle.allutils.fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.youle.allutils.R;

public class FindFragment extends BaseFragment {

	// 选择开关
	@ViewInject(R.id.fragment_find_llyt_switch)
	LinearLayout fragment_find_llyt_switch;

	// 品牌找车
	@ViewInject(R.id.fragment_find_tv_brand)
	TextView fragment_find_tv_brand;

	// 精准找车
	@ViewInject(R.id.fragment_find_tv_filter)
	TextView fragment_find_tv_filter;

	// fragment事务
	private FragmentTransaction ft;

	// 品牌找车
	private FindBrandFragment findbrandFragment;

	// 精准找车
	private FindFilterFragment findfilterFragment;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_find_main;
	}

	@Override
	protected void initParams() {
		viewOnClick(fragment_find_tv_brand);
	}

	@OnClick({ R.id.fragment_find_tv_brand, R.id.fragment_find_tv_filter })
	public void viewOnClick(View view) {
		ft = getChildFragmentManager().beginTransaction();
		switch (view.getId()) {
		case R.id.fragment_find_tv_brand:
			fragment_find_tv_brand.setBackgroundColor(getResources().getColor(
					R.color.find_car));
			fragment_find_tv_brand.setTextColor(getResources().getColor(
					R.color.find_default_car));
			fragment_find_tv_filter.setBackgroundColor(getResources().getColor(
					R.color.find_default_car));
			fragment_find_tv_filter.setTextColor(getResources().getColor(
					R.color.find_car));

			if (findbrandFragment == null) {
				findbrandFragment = new FindBrandFragment();
			}

			ft.replace(R.id.fragment_find_flyt_content, findbrandFragment);
			break;
		case R.id.fragment_find_tv_filter:
			fragment_find_tv_brand.setBackgroundColor(getResources().getColor(
					R.color.find_default_car));
			fragment_find_tv_brand.setTextColor(getResources().getColor(
					R.color.find_car));
			fragment_find_tv_filter.setBackgroundColor(getResources().getColor(
					R.color.find_car));
			fragment_find_tv_filter.setTextColor(getResources().getColor(
					R.color.find_default_car));
			if (findfilterFragment == null) {
				findfilterFragment = new FindFilterFragment();
			}
			ft.replace(R.id.fragment_find_flyt_content, findfilterFragment);
			break;
		default:
			break;
		}
		ft.commit();
	}
}
