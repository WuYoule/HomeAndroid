package com.wqy.dianping;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wqy.dianping.fragment.FragmentHome;
import com.wqy.dianping.fragment.FragmentMy;
import com.wqy.dianping.fragment.FragmentSearch;
import com.wqy.dianping.fragment.FragmentTuan;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	@ViewInject(R.id.main_bottom_tabs)
	private RadioGroup group;

	@ViewInject(R.id.main_home)
	private RadioButton main_home;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_actiivity);
		ViewUtils.inject(this);
		
		//��ʼ��FragmentManager
		fragmentManager=getSupportFragmentManager();
		//����Ĭ��ѡ��
		main_home.setChecked(true);
		group.setOnCheckedChangeListener(this);
		
		//Ĭ����ҳfragment
		changeFragment(new FragmentHome(), false);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		switch (checkedId) {
		case R.id.main_home://��ҳ
			changeFragment(new FragmentHome(), true);
			break;
		case R.id.main_search://����
			changeFragment(new FragmentSearch(), true);
			break;
		case R.id.main_tuan://�Ź�
			changeFragment(new FragmentTuan(), true);
			break;
		case R.id.main_my://�ҵ�
			changeFragment(new FragmentMy(), true);
			break;

		default:
			break;
		}
		
	}
	//�л���ͬ��fragment
	public void changeFragment (Fragment fragment,boolean isFirst){
		
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.main_content, fragment);
		if (!isFirst) {
			fragmentTransaction.addToBackStack(null);
		}
		fragmentTransaction.commit();
	}
}
