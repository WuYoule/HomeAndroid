package com.youle.allutils.util;

import android.content.Context;
import android.os.Environment;

public class JFileKit {

	public static String getDiskCacheDir(Context context) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			cachePath=context.getExternalCacheDir().getAbsolutePath();
		}
		else {
			cachePath=context.getCacheDir().getAbsolutePath();
		}
		return cachePath;
		
	}
}
