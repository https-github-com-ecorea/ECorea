package com.project.ecorea.controller.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(basePackages = "com.project.ecorea.controller.mvc")
public class MvcAdvice {
	@ExceptionHandler(ConstraintViolationException.class)
	public String constraintViolationException(ConstraintViolationException e, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", e.getMessage());
		return "redirect:/system/error";
	}
}
