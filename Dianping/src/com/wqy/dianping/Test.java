package com.wqy.dianping;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


public class Test extends Activity {

	
	@ViewInject(R.id.myclick)
     private View myclick11;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.test);
	   ViewUtils.inject(this);
	}
	
	@OnClick(R.id.myclick)
	public void ok(View view) {
		Toast.makeText(Test.this, "you click", Toast.LENGTH_SHORT).show();
	}
}
