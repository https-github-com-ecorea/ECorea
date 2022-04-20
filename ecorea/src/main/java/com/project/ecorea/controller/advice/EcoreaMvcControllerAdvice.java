package com.project.ecorea.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(basePackages = "com.project.ecorea.controller.mvc")
public class EcoreaMvcControllerAdvice {
	
	/* 메소드의 파라미터나 리턴 값에 문제가 있을 때의 오류 (500) */
	@ExceptionHandler(ConstraintViolationException.class)
	public String constraintViolationException(ConstraintViolationException e, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", e.getMessage()); /* 메시지를 뷰(error.html)에서 출력 가능 */
		return "redirect:/error";
	}

}