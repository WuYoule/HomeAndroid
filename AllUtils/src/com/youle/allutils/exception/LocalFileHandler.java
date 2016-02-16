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
 * 本地异常处理类
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
	 * 自定义错误处理 收集错误信息发送错误报告等操作在此完成
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
	                Toast.makeText(context, "对不起，程序出现异常,正在从错误中恢复",Toast.LENGTH_SHORT).show();
	                Looper.loop();
	            }
	        }).start();
        
		//保存错误日志
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
			out.write(("\n\n-----------------错误分割线  "+new Date()+"-----------------\n\n").getBytes());
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
