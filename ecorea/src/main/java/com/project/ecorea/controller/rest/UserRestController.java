package com.project.ecorea.controller.rest;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecorea.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserRestController {
	private UserService service;
	
	@GetMapping("/user/find/id")
	public ResponseEntity<String> findUserId(String email, String name, Principal principal) {
		if(principal!=null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다");
		
		String id = service.findUserId(name, email);
		if(id==null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디를 찾지 못했습니다");
		return ResponseEntity.ok(id);
	}
}