package com.youle.allutils.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.text.format.DateFormat;

/**
 * ϵͳ�쳣�������
 * @author Administrator
 *
 */
public abstract class BaseExceptionHandler implements UncaughtExceptionHandler {
	public static final String TAG="CrashHandler";
//	protected static final DateFormat dateFormat=DateFormat.getda

	/**
	 * δ�����쳣��ת
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
	   //�����ȷ������δ�����쳣
		if (handlerException(ex)) {
              try {
				//��������� �ó����������3���˳� ��֤������־�ı���
            	  Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
               //�˳�����
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
