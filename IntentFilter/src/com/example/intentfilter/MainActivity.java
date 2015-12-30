package com.example.intentfilter;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setAction("MY_INTENTFILTER");
				//intent.setData(Uri.parse("http://www.baidu.com"));
				intent.addCategory("MY_CAT");
				//intent.putExtra("name", "WQY");
				
				Intent intent2=new Intent(Intent.ACTION_DIAL);
				intent2.setData(Uri.parse("tel:18221737451"));
				
				Intent intent3=new Intent();
				intent3.setData(Uri.parse("http://www.baidu.com"));
				
				startActivity(intent3);
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
