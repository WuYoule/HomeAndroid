package com.myqq.Index;

import java.util.List;

import com.example.myqq.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class MyTabActivity extends FragmentActivity {
	private FragmentTabHost mTabHost = null;
	private List<Fragment> list;
	private RelativeLayout rl;
    private	View view ;
    private	int tabcount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_activity);

		// TabHost tabHost=getTabHost();
		// Intent intent=new Intent(MyTabActivity.this,MessageActivity.class);
		// Resources re=getResources();
		// TabHost.TabSpec spec1=tabHost.newTabSpec("消息");
		//
		// spec1.setIndicator("消息",
		// re.getDrawable(R.drawable.text_message_48px_1187408_easyicon));
		// spec1.setContent(intent);
		// tabHost.addTab(spec1);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

		mTabHost.setup(this, getSupportFragmentManager(),R.id.realtabcontent);

		mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(getTabItemView(0)),
				MessageFragment.class, null);
		
		mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(getTabItemView(1)),
				ContactFragment.class, null);
		
		mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(getTabItemView(2)),
				TrendsFragment.class, null);
		
		
		//获取tab的个数
		tabcount=mTabHost.getTabWidget().getChildCount();
		
		//初始化第一个tab的颜色为灰色（选中状态）
		view= mTabHost.getTabWidget().getChildAt(0);
		rl=(RelativeLayout) view.findViewById(R.id.tab_item_rl);
		rl.setBackgroundResource(R.color.mygray03);
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				mTabHost.setCurrentTabByTag(tabId);
				//根据不同选中项改变背景颜色
				updateTab();
			}
		});

	}

	

	//改变选中项的背景颜色
	protected void updateTab() {
		
		for (int i = 0; i < tabcount; i++) {
			
			 view = mTabHost.getTabWidget().getChildAt(i);
			 rl=(RelativeLayout) view.findViewById(R.id.tab_item_rl);
			if (mTabHost.getCurrentTabTag()==String.valueOf(i)) {
				rl.setBackgroundResource(R.color.mygray03);
			}
			else {
				rl.setBackgroundResource(R.color.mywhite);
			}
		}
		
	}



	private View getTabItemView(int index) {
		View view;
		RelativeLayout rl;
		 view = LayoutInflater.from(this).inflate(R.layout.tab_item_layout, null);
			TextView tv=(TextView) view.findViewById(R.id.tab_item_tv);
			
		
		
		switch (index) {
		case 0:
			
			tv.setText("消息");
			break;
		case 1:
			
			tv.setText("联系人");
			break;
		case 2:
		
			tv.setText("动态");
			break;

		default:
			break;
		}
	    
	  

	    return view;
	  }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		  mTabHost = null;

	}
}
