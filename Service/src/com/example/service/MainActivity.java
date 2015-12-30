package com.example.service;


import com.example.service.Myservice.DownloadBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button startService;
	Button stopService;
	
	Button bindService;
	Button unbindService;
	
	
	private Myservice.DownloadBinder downloadBinder;

	private ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
		    downloadBinder=(DownloadBinder) service;
		    downloadBinder.startDownload();
		    downloadBinder.getProcess();
		    downloadBinder.getService().HelloWorld();
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService=(Button) findViewById(R.id.start_service);
        stopService=(Button) findViewById(R.id.stop_service);
        
        bindService=(Button) findViewById(R.id.bind_service);
        unbindService=(Button) findViewById(R.id.unbind_service);
        
        startService.setOnClickListener(new myClickListener());
        stopService.setOnClickListener(new myClickListener());
        
        bindService.setOnClickListener(new myClickListener());
        unbindService.setOnClickListener(new myClickListener());
        
    }

    class myClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.start_service:
				Intent startIntent=new Intent(MainActivity.this, Myservice.class);
				startService(startIntent);
				break;
			case R.id.stop_service:
				Intent stopIntent=new Intent(MainActivity.this, Myservice.class);
				stopService(stopIntent);
				break;
			case R.id.bind_service:
				Intent bindIntent=new Intent(MainActivity.this, Myservice.class);
				bindService(bindIntent, conn, BIND_AUTO_CREATE);
				break;
			case R.id.unbind_service:
			
				unbindService(conn);
				break;
				


			default:
				break;
			}
			
		}
    
    }
   
    
}
