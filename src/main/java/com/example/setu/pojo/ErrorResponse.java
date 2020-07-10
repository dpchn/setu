package com.example.setu.pojo;

public class ErrorResponse {

	String errorCode;
	int code;

	public ErrorResponse() {

	}

	public String getErrorCode() {
		return errorCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
