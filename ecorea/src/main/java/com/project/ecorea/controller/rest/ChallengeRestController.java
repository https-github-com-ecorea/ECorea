package com.project.ecorea.controller.rest;

import java.security.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@RestController
@AllArgsConstructor
public class ChallengeRestController {
	private ChProveService chProveService;
	
	@GetMapping(value="/challengeDetail/provelist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingChProveDto challengeDetailProveList(Criteria cri) {
		return chProveService.challengeDetailProveList(cri);
	}
	
	// 일반회원 : 챌린지 신청하기
	@PostMapping("/challenge/chapply")
	public ResponseEntity<String> chApplyCheck(Principal principal, Integer cno) {
		chProveService.chApplyCheck(principal.getName(), cno);
		return null;
	}
}
