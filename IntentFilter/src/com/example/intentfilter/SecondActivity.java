package com.example.intentfilter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.second_layout);
		Intent intent=this.getIntent();
		//String str=	intent.getDataString();
		//String name=intent.getExtras().getString("name");
		//Log.i("Youle", str+"--"+ name);
	}

	
}
