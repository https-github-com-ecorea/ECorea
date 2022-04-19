package com.project.ecorea.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionAdvice {
	
	/* 메소드의 파라미터나 리턴 값에 문제가 있을 때 오류 (500) */
	@ExceptionHandler(ConstraintViolationException.class)
	public String constraintViolationException(ConstraintViolationException e, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", e.getMessage());
		return "redirect:/system/error";
	}
	
}
