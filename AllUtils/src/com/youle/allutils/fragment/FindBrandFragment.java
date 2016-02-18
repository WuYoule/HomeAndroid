package com.youle.allutils.fragment;

//import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import za.co.immedia.pinnedheaderlistview.PinnedHeaderListView;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.youle.allutils.R;
//import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.adapter.PinnedSectionedAdapter;
import com.youle.allutils.view.SideBar;

public class FindBrandFragment extends BaseFragment {

	@ViewInject(R.id.pinnedListView)
	PinnedHeaderListView find_brand_lv;
	
	
	@ViewInject(R.id.find_brand_sb)
	SideBar find_brand_sb;
	
	
	private WindowManager windowManager;
	
	//提示对话框
	@ViewInject(R.id.xianshi_tv)
	private TextView dialogText;
	
	
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
        
        //初始化提示对话框
//        dialogText=(TextView) LayoutInflater.from(context).inflate(R.layout.fragment_find_brand_ic_index, null);
//        dialogText.setVisibility(View.VISIBLE);
//        
        //初始化窗口管理器
//        windowManager=(WindowManager) context.getSystemService(context.WINDOW_SERVICE);
//        WindowManager.LayoutParams lp=new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        
//        //将对话框添加到窗口中
//        windowManager.addView(dialogText, lp);
        
        //设置侧边栏信息
       find_brand_sb.setTextView(dialogText);
      find_brand_sb.setListView(find_brand_lv);
//       find_brand_sb.getPinnedSectionedAdapter(sectionedAdapter);
       
	}
	


}
