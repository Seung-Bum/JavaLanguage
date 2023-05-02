package com.items.controller;

public class ResultMap {
	  public static final String SUCCESS = "success";
	  public static final String FAIL = "fail";

	  private String status;
	  private Object data;
	  
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}  
}
