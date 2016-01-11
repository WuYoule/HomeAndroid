package com.myqq.start;

import com.example.myqq.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MyqqStartActivtiy extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.start_activity);
    	ImageView infoOperatingIV = (ImageView)findViewById(R.id.infoOperating); 
    	
    	//Í¼Æ¬²»Í£Ðý×ª
    	Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.load); 
    	LinearInterpolator lin = new LinearInterpolator(); 
    	operatingAnim.setInterpolator(lin); 
    	infoOperatingIV.startAnimation(operatingAnim);
    	
    	
    	new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
			    startActivity(new Intent(MyqqStartActivtiy.this, ViewPagerActivity.class));
				
			}
		}, 500);
    }

}
