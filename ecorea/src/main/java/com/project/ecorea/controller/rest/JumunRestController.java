package com.project.ecorea.controller.rest;

import java.security.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@RestController
public class JumunRestController {
	private JumunService jumunService;
	
	@GetMapping("/order/pay/checkPoint")
	public ResponseEntity<Void> checkUsePoint(Integer usePoint, Principal principal) {
		Boolean result = jumunService.checkPoint(usePoint, principal.getName());
		if(result==true)
			return ResponseEntity.ok(null);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
}
