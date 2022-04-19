package com.project.ecorea.controller.rest;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecorea.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserRestController {
	private UserService service;
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/user/overlap/id")
	public ResponseEntity<String> findOverlapId(String id, Principal principal) {
		if(principal!=null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다");
		
		if(service.findOverlapId(id)==false)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("사용이 불가능한 아이디입니다");
		return ResponseEntity.status(HttpStatus.OK).body("사용가능한 아이디입니다");
	}
	
	/* 아이디 찾기 */
	@PreAuthorize("isAnonymous()")
	@GetMapping("/user/find/id")
	public ResponseEntity<String> findUserId(String email, String name, Principal principal) {
		if(principal!=null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다");
		
		String id = service.findUserId(name, email);
		if(id==null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디를 찾지 못했습니다");
		return ResponseEntity.ok(id);
	}
	
	/* 비밀번호 초기화 */
	@PreAuthorize("isAnonymous()")
	@GetMapping("/user/find/password")
	public ResponseEntity<String> resetUserPw(String id, String email, Principal principal) {
		if(principal!=null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 접근입니다");
		
		Boolean result = service.resetUserPw(id, email);
		if(result==false) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디와 이메일을 확인해주세요");
		}
		return ResponseEntity.ok("임시비밀번호를 이메일로 전송했습니다");
	}
}
