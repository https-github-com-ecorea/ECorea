package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorMvcController {

	/* 상황에 따라 다른 오류 메시지를 띄울 화면 */
	@GetMapping("/system/error")
	public void error() {
	}
	
}
