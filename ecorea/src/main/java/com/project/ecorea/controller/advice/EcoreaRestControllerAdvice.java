package com.project.ecorea.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestControllerAdvice(basePackages = "com.project.ecorea.controller.rest")
public class EcoreaRestControllerAdvice {

	/* 메소드의 파라미터나 리턴 값에 문제가 있을 때의 오류 (500) */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> constraintVoilationException(ConstraintViolationException e, RedirectAttributes ra) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

}