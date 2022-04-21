package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ecorea.dto.AbstractException;
import com.project.ecorea.dto.PageError;

@Controller
public class ExceptionController {
	
	@GetMapping("/exception")
	public String error() {
		throw new AbstractException();
	}
	
	@ExceptionHandler(AbstractException.class)
	public @ResponseBody PageError error(AbstractException e) {
		PageError pageError = new PageError();
		pageError.setMessage("Error");
		pageError.setStatus("status");
		return pageError;
	}
	
	@GetMapping("/system/error")
	public void systemError() {
	}
	
}