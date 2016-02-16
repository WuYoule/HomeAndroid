package com.youle.allutils.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

/**
 * ϵͳ�쳣�������
 * @author Administrator
 *
 */
public abstract class BaseExceptionHandler implements UncaughtExceptionHandler {
	public static final String TAG="CrashHandler";
	/**
	 * δ�����쳣��ת
	 */
	@Override
	public void uncaughtException(Thread thread,final Throwable ex) {

	   //�����ȷ������δ�����쳣
		if (handlerException(ex)) {
              try {
				//��������� �ó����������3���˳� ��֤������־�ı���
            	  Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
               //�˳�����
              Log.i(TAG, "BaseException....................................");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
		}
	}
	
	
	/**
	 * �Զ���������ռ�������Ϣ���ͱ���Ⱦ��ڴ���� �ɸ����Լ�������Զ����쳣����
	 * @param ex
	 * @return
	 */
	public abstract boolean handlerException(Throwable ex);

}
