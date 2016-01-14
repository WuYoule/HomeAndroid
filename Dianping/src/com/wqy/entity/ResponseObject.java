package com.wqy.entity;

public class ResponseObject<T> {

	private String state;
	private T datas;
	public ResponseObject(String state, T datas) {
		super();
		this.state = state;
		this.datas = datas;
	}
	public ResponseObject() {
		super();
	
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
	
}
