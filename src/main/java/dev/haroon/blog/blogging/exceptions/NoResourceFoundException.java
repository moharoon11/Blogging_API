package dev.haroon.blog.blogging.exceptions;

import lombok.Data;

@Data
public class NoResourceFoundException extends RuntimeException {

	private String resourceName;
	
	private String message;
	
	
	
	public NoResourceFoundException(String resourceName, String message) {
		super(message + resourceName);
		this.message = message;
		this.resourceName = resourceName;
	}
	
}
