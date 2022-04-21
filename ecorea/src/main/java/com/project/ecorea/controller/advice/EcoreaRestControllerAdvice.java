package com.project.ecorea.controller.advice;

import java.sql.*;

import javax.validation.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(basePackages = "com.project.ecorea.controller.rest")
public class EcoreaRestControllerAdvice {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
		return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullPointerException(NullPointerException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> sqlException(SQLException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Internal Error");
	}
}