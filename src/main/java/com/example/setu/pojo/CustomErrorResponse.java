package com.example.setu.pojo;

public class CustomErrorResponse {

	String errorCode;
	String status;

	public CustomErrorResponse(String errorCode, String status) {
		super();
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}