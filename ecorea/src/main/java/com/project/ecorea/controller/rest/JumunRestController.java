package com.project.ecorea.controller.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@RestController
public class JumunRestController {
	private JumunService jumunService;
	
	@GetMapping("/order/pay/checkPoint")
	public ResponseEntity<Void> checkUsePoint(Integer usePoint) {
		String memberId = "zzzzuny";
		Boolean result = jumunService.checkPoint(usePoint, memberId);
		if(result==true)
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
}
