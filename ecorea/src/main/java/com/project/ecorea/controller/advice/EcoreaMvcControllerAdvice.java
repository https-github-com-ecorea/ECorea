package com.project.ecorea.controller.advice;

import java.sql.*;

import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice(basePackages = "com.project.ecorea.controller.mvc")
public class EcoreaMvcControllerAdvice {
	
	/* 메소드의 파라미터나 리턴 값에 문제가 있을 때 오류 (500) */
	@ExceptionHandler(ConstraintViolationException.class)
	public String constraintViolationException(ConstraintViolationException e, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", e.getMessage());
		return "redirect:/system/error";
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public String nullPointException(NullPointerException e, RedirectAttributes ra) {
		ra.addFlashAttribute("errormsg","잘못된 접근입니다. " + e.getClass());
		return "redirect:/system/error";
	}
	
	
	@ExceptionHandler(SQLException.class) 
	public String sqlException(SQLException e, RedirectAttributes ra) {
		ra.addFlashAttribute("errormsg", "서버 오류입니다." + e.toString());
		return "redirect:/";
	}

}