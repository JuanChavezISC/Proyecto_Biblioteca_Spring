package com.biblioteca.exception;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationBody(MethodArgumentNotValidException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
				"Validation Failed", "Hay errores de validacion",
				req.getRequestURI());
		
		err.setFieldErrors(
				ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> new ApiError.FieldErrorItem(fe.getField(),
						fe.getDefaultMessage()))
				.collect(Collectors.toList())
				);
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> handleValidationParams(ConstraintViolationException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
				"Constraint Violation", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(NotFoundException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(HttpStatus.NOT_FOUND.value(),
				"Not Found", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleIntegrity(DataIntegrityViolationException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(HttpStatus.CONFLICT.value(),
				"Data Integrity Violation",
				"No se pudo completar la operacion por restricciones de datos",
				req.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneral(Exception ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error", ex.getMessage(), req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
}
