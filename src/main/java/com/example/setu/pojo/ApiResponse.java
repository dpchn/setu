package com.example.setu.pojo;

public class ApiResponse {

	int statusCode;
	String errorCode;
	String status;
	Object data;

	public ApiResponse(int statusCode, String status, Object data) {
		this.statusCode = statusCode;
		this.status = status;
		this.data = data;
	}
	public ApiResponse() {

	}

	public ApiResponse(int code, String errorCode, String status) {
		this.statusCode = code;
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

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
