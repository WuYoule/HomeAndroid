package com.youle.allutils.exception;

/**
 * 自定义异常类  
 * 系统中所有异常类都转化成Exception
 * @author Administrator
 *
 */
public class BaseException extends RuntimeException {
	public BaseException() {
		super();
	}
	
	public BaseException(String detail,Throwable throwable){
		super(detail, throwable);
		
	}
	public BaseException(String detail){
		super(detail);
		
		
	}
	public BaseException(Throwable throwable){
		super(throwable);
		
		
	}
	

}
