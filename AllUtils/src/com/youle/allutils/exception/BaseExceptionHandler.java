package com.youle.allutils.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import android.text.format.DateFormat;

/**
 * 系统异常处理基类
 * @author Administrator
 *
 */
public abstract class BaseExceptionHandler implements UncaughtExceptionHandler {
	public static final String TAG="CrashHandler";
//	protected static final DateFormat dateFormat=DateFormat.getda

	/**
	 * 未捕获异常跳转
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
	   //如果正确处理了未捕获异常
		if (handlerException(ex)) {
              try {
				//如果处理了 让程序继续运行3秒退出 保证错误日志的保存
            	  Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
               //退出程序
             android.os.Process.killProcess(android.os.Process.myPid());
             System.exit(1);
		}
	}
	/**
	 * 自定义错误处理收集错误信息发送报告等均在此完成 可根据自己情况来自定义异常处理
	 * @param ex
	 * @return
	 */
	public abstract boolean handlerException(Throwable ex);

}
