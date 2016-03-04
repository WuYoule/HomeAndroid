package com.youle.allutils.util;

import com.youle.allutils.application.LocalApplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class VersionUtil {
  /**
   *���ص�ǰ����汾��versionName
   */
	public static String getAppVersionName(){
		String versionName="";
		try {
			PackageManager pm=LocalApplication.getInstance().getPackageManager();
			PackageInfo pi=pm.getPackageInfo(LocalApplication.getInstance().getPackageName(), 0);
			versionName=pi.versionName;
			if (versionName==null||versionName.length()<=0) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * ���ص�ǰ����İ汾�ĺ�
	 */
	public static Integer getAppVersionCode(){
		try {
			PackageManager pm=LocalApplication.getInstance().getPackageManager();
			PackageInfo pi=pm.getPackageInfo(LocalApplication.getInstance().getPackageName(), 0);
			return pi.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
