package com.youle.allutils.fragment;

import org.xmlpull.v1.XmlPullParser;

import com.lidroid.xutils.ViewUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{

	public Context context;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context=activity;
	}

	protected Dialog dialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(getLayoutId(), container,false);
	   ViewUtils.inject(this,view);
	   initParams();
	   return view;
	}

	protected abstract int getLayoutId();
	
	protected abstract void initParams();
}
