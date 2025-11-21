package com.biblioteca.exception;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ------------------ Seguridad ------------------
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Credenciales inválidas", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {
        return build(HttpStatus.FORBIDDEN, "Acceso denegado", "No tienes permisos para este recurso");
    }
        // ------------------ Validaciones ------------------

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationBody(MethodArgumentNotValidException ex
			,HttpServletRequest req) {

        List<ApiError.FieldErrorItem> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ApiError.FieldErrorItem(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

		ApiError err = new ApiError(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
				"Validation Failed",
                "Hay errores de validacion",
                req.getRequestURI(),
				errores);
        
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> handleValidationParams(ConstraintViolationException ex
			,HttpServletRequest req) {

		ApiError err = new ApiError(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                ex.getMessage(),
                req.getRequestURI(),
                null);
		return ResponseEntity.badRequest().body(err);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(NotFoundException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
				"Not Found",
                ex.getMessage(),
                req.getRequestURI(),
                null);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

    // ------------------ Integridad ------------------
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleIntegrity(DataIntegrityViolationException ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
				"Data Integrity Violation",
				"No se pudo completar la operacion por restricciones de datos",
				req.getRequestURI(),
                null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

    // ------------------ Errores Generales ------------------
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneral(Exception ex
			,HttpServletRequest req) {
		
		ApiError err = new ApiError(
                OffsetDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error",
                ex.getMessage(),
                req.getRequestURI(),
                null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
	
}
