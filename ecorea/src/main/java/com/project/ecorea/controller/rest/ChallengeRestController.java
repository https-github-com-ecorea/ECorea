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
	private ChallengeService challengeService;
	
	@GetMapping(value="/challengeDetail/provelist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingChProveDto challengeDetailProveList(Criteria cri) {
		return chProveService.challengeDetailProveList(cri);
	}
	
	// 일반회원 : 챌린지 신청하기
	@PostMapping("/challenge/chapply")
	public ResponseEntity<String> chApplyCheck(Principal principal, Integer cno) {
		if(chProveService.chApplyCheck(principal.getName(), cno) == true) {
			return ResponseEntity.status(HttpStatus.OK).body("신청에 성공했습니다");			
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 신청한 챌린지입니다");
		}
	}
	
	@PostMapping("/challenge/corp/deleteChallenge")
	public ResponseEntity<String> deleteChallenge(Integer cno) {
		Boolean result = challengeService.deleteChallenge(cno);
		if(result == true) {
			return ResponseEntity.status(HttpStatus.OK).body("해당 챌린지가 삭제되었습니다");
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("삭제에 실패했습니다");
		}
	}
}
