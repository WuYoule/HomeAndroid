package com.youle.allutils.fragment;

//import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youle.allutils.R;
//import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.adapter.PinnedSectionedAdapter;

public class FindBrandFragment extends BaseFragment {

	@Override
	protected int getLayoutId() {
		
		return R.layout.fragment_find_brand_main;
	}

	@Override
	protected void initParams() {
		
	}

	@Override
	protected void initParams(View view) {
		PinnedHeaderListView listView = (PinnedHeaderListView) view.findViewById(R.id.pinnedListView);
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout header1 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//        ((TextView) header1.findViewById(R.id.textItem)).setText("HEADER 1");
//        LinearLayout header2 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//        ((TextView) header2.findViewById(R.id.textItem)).setText("HEADER 2");
//        LinearLayout footer = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//        ((TextView) footer.findViewById(R.id.textItem)).setText("FOOTER");
//       listView.addHeaderView(header1);
//        listView.addHeaderView(header2);
//        listView.addFooterView(footer);
        PinnedSectionedAdapter sectionedAdapter = new PinnedSectionedAdapter();
        listView.setAdapter(sectionedAdapter);
	}
	
//	@Override
	//public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		 View view=inflater.inflate(R.layout.fragment_find_brand_main, container,false);
//			PinnedHeaderListView listView = (PinnedHeaderListView) view.findViewById(R.id.pinnedListView);
//       LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//       LinearLayout header1 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//       ((TextView) header1.findViewById(R.id.textItem)).setText("HEADER 1");
//       LinearLayout header2 = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//       ((TextView) header2.findViewById(R.id.textItem)).setText("HEADER 2");
//       LinearLayout footer = (LinearLayout) inflator.inflate(R.layout.list_item, null);
//       ((TextView) footer.findViewById(R.id.textItem)).setText("FOOTER");
//       listView.addHeaderView(header1);
//       listView.addHeaderView(header2);
//       listView.addFooterView(footer);
//       PinnedSectionedAdapter sectionedAdapter = new PinnedSectionedAdapter();
//       listView.setAdapter(sectionedAdapter);
//		 return view;
//	}

}
