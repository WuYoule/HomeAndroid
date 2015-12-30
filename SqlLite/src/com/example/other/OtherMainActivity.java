package com.example.other;


import com.example.sqllite.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OtherMainActivity extends Activity {
	
	private MyDatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_layout);
		dbHelper=new MyDatabaseHelper(this, "BookStore.db", null, 3);
		Button createBtn=(Button) findViewById(R.id.create_database);
		createBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dbHelper.getWritableDatabase();
				
			}
		});
	}
}
