package com.youle.allutils.exception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import com.youle.allutils.util.JFileKit;

import android.R.string;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * �����쳣������
 * @author Administrator
 *
 */
public class LocalFileHandler extends BaseExceptionHandler {
	private static final String TAG="LocalFileHandler";
	

	private Context context;
	public LocalFileHandler(Context context){
		this.context=context;
	}

	/**
	 * �Զ�������� �ռ�������Ϣ���ʹ��󱨸�Ȳ����ڴ����
	 */
	@Override
	public boolean handlerException(Throwable ex) {
		
		if (ex == null) {
			return false;	
		}
		 new Thread(new Runnable() {
	            @Override
	            public void run() {
	                Looper.prepare();
	                Toast.makeText(context, "�Բ��𣬳�������쳣,���ڴӴ����лָ�",Toast.LENGTH_SHORT).show();
	                Looper.loop();
	            }
	        }).start();
        
		//���������־
         saveLog(ex);
		return true;
	}

	private void saveLog(Throwable ex) {
		
		try {
			File errorFile=new File(JFileKit.getDiskCacheDir(context)+"/log/crash.log");
			if (!errorFile.exists()) {
				errorFile.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(errorFile,true);
			out.write(("\n\n-----------------����ָ���  "+new Date()+"-----------------\n\n").getBytes());
			PrintStream stream=new PrintStream(out);
			ex.printStackTrace(stream);
			
			stream.flush();
			out.flush();
			stream.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
