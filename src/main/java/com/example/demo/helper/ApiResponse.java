package com.example.demo.helper;

public class ApiResponse<T> {

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String gettype(){
		return type;
	}
	public void settype(String type){
		this.type = type;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public ApiResponse(boolean success, String message,  T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public ApiResponse(boolean success, String message, String type, T data) {
		this.success = success;
		this.message = message;
		this.type = type;
		this.data = data;
	}

	private boolean success;
	private String message;
	private String type;
	private T data;
}
