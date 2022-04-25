package com.project.ecorea.controller.rest;

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
}
