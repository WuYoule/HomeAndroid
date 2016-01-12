package com.wqy.dianping.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;


//标记的读取
public class SharedUtils {
    private static final String FILE_NAME="dianping";
    private static final String MODE_NAME="welcome";
    public static  boolean getWelcomeBoolean(Context context) {
    	
    	return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    			.getBoolean(MODE_NAME, false);
		
	}
    
    public static void  putWelcomeBoolean(Context context,boolean isFirst) {
		Editor editor=context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		editor.putBoolean(MODE_NAME, isFirst);
		editor.commit();
	}
}
