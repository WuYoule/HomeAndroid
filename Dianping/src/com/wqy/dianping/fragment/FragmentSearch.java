package com.wqy.dianping.fragment;


import java.net.NetPermission;

import com.wqy.dianping.NearbyMapActivity;
import com.wqy.dianping.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSearch extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.search_index, null);
		startActivity(new Intent(getActivity(), NearbyMapActivity.class));
		return view;
		
	}
}
