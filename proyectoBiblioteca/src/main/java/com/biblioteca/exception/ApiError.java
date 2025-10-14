package com.biblioteca.exception;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

	private OffsetDateTime timeStamp = OffsetDateTime.now();
	private int status;
	private String error;
	private String message;
	private String path;
	private List<FieldErrorItem> fieldErrors;
	
	public static class FieldErrorItem{
		public String field;
		public String message;
		
		public FieldErrorItem(String field, String message) {
			super();
			this.field = field;
			this.message = message;
		}
	}

	public ApiError() {	}

	public ApiError(int status, String error, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FieldErrorItem> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorItem> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	
	
}
