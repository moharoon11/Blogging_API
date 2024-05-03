package dev.haroon.blog.blogging.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;



public class ApiResponse {

	
	private String message;
	private boolean success;
	
	public ApiResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}
	
	

	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", success=" + success + "]";
	}
	
	
	
}
