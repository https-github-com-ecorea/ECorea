package com.project.ecorea.controller.rest;

import java.security.*;

import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.*;

@RequiredArgsConstructor
@RestController
@Secured("ROLE_MEMBER")
public class CartRestController {
	private final CartService cartService;
	
	// 수량 증가
	@PatchMapping("/order/cart/plusProduct/{pno}")
	public ResponseEntity<Integer> plusCartProduct(@PathVariable Integer pno, Principal principal) {
		Integer productCnt = cartService.plusCnt(principal.getName(), pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt);
		
	}
	
	// 수량 감소
	@PatchMapping("/order/cart/minusProduct/{pno}")
	public ResponseEntity<Integer> minusCartProduct(@PathVariable Integer pno, Principal principal) {
		Integer productCnt = cartService.minusCnt(principal.getName(), pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt); 
	}
	
}
