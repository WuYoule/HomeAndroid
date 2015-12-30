package com.example.bdwall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends Fragment{

	SettingItemView zidong;
	SettingItemView yijian;
	SettingItemView tupianzhiliang;
	SettingItemView clear;
	SettingItemView jiancegenxin;
	SettingItemView help;
	SettingItemView ideaback;
	SettingItemView contactus;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    View view=inflater.inflate(R.layout.setting_fragment,container,false);
		    findView(view);
	        return  view;
	}
   
    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		initData();
	}


	private void initData() {
		zidong.setLeftText("自动更换图片");
		zidong.setRightBitmap(R.drawable.image_more_subitem_arrow);
		
		yijian.setLeftText("一键更换图片");
		yijian.setRightBitmap(R.drawable.image_more_subitem_arrow);
		
		tupianzhiliang.setLeftText("图片浏览质量");
		tupianzhiliang.setRightText("自动");
		
		jiancegenxin.setLeftText("检查更新");;
		
		clear.setLeftText("清理缓存");
		
		help.setLeftText("帮助手册");
		help.setRightBitmap(R.drawable.image_more_subitem_arrow);
		
		ideaback.setLeftText("意见反馈");
		ideaback.setRightBitmap(R.drawable.image_more_subitem_arrow);
		
		
		contactus.setLeftText("联系我们");
		contactus.setRightBitmap(R.drawable.image_more_subitem_arrow);
		
	}


	private void findView(View view) {
		zidong=(SettingItemView) view.findViewById(R.id.zidong);
		yijian=(SettingItemView) view.findViewById(R.id.yijian);
		tupianzhiliang=(SettingItemView) view.findViewById(R.id.tupianzhiliang);
		clear=(SettingItemView) view.findViewById(R.id.clear);
		jiancegenxin=(SettingItemView) view.findViewById(R.id.jiancegenxin);
		help=(SettingItemView) view.findViewById(R.id.help);
		ideaback=(SettingItemView) view.findViewById(R.id.ideaback);
		contactus=(SettingItemView) view.findViewById(R.id.contactus);
		
		
	}

}
