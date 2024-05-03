package dev.haroon.blog.blogging.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	private String resourceName;
	private String feildName;
	private Object fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(resourceName + " Not found for the " + fieldName + " " + fieldValue);
		this.resourceName = resourceName;
		this.feildName = fieldName;
		this.fieldValue = fieldValue;
	}

}
