package com.youle.allutils.exception;

/**
 * �Զ����쳣��  
 * ϵͳ�������쳣�඼ת����Exception
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
