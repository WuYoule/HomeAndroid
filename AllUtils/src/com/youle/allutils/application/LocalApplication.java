package com.youle.allutils.application;

import com.youle.allutils.exception.BaseExceptionHandler;

public class LocalApplication extends BaseApplication{

	
	
	
	@Override
	public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
		return null;
	}

}
