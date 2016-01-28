package com.wqy.dianping.fragment;


import javax.security.auth.login.LoginException;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wqy.dianping.MyLoginActivity;
import com.wqy.dianping.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMy extends Fragment implements OnClickListener {

	@ViewInject(R.id.my_index_login_text)
	private TextView my_index_login_text;
	
	@ViewInject(R.id.my_index_login_image)
	private ImageView my_index_login_image;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.my_index, null);
		ViewUtils.inject(this,view);
		my_index_login_image.setOnClickListener(this);
		my_index_login_text.setOnClickListener(this);
		return view;
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_index_login_text:
			Login();
			
			break;
		case R.id.my_index_login_image:
			break;
		default:
			break;
		}
		
	}
	private void Login() {
		Intent intent=new Intent(getActivity(),MyLoginActivity.class);
		startActivityForResult(intent, 1);
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1&&resultCode==1) {
			my_index_login_text.setText(data.getStringExtra("login_name"));
			
		}
	}
}
