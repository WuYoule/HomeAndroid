package com.youle.allutils.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class JFileKit {

	private static final String TAG="JFileKit";
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
