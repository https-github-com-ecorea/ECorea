package com.project.ecorea.controller.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.*;

@RequiredArgsConstructor
@RestController
public class CartRestController {
	private final CartService cartService;
	String memberId = "zzzzuny";
	
	@PatchMapping("/order/cart/plusProduct/{pno}")
	public ResponseEntity<Integer> plusCartProduct(@PathVariable Integer pno) {
		Integer productCnt = cartService.plusCnt(memberId, pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt);
		
	}
	
	@PatchMapping("/order/cart/minusProduct/{pno}")
	public ResponseEntity<Integer> minusCartProduct(@PathVariable Integer pno) {
		Integer productCnt = cartService.minusCnt(memberId, pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt); 
	}
	
}
