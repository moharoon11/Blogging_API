package dev.haroon.blog.blogging.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.haroon.blog.blogging.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		return ResponseEntity.ok(new ApiResponse(ex.getMessage(), false));
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiResponse> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
		return ResponseEntity.ok(new ApiResponse(ex.getMessage(), false));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler (
			MethodArgumentNotValidException ex) {
		
		Map<String, String> response = new HashMap<>();
		
		ex.getBindingResult()
		  .getAllErrors()
		  .forEach((error) -> {
			  String fieldName = ((FieldError) error).getField();
			  String message = error.getDefaultMessage();
			  response.put(fieldName, message);
		  });
		
	
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}


}
