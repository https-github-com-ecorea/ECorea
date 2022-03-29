package com.project.ecorea.controller.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

@RestController
public class CartRestController {
	@Autowired
	CartService cartService;
	
	@PatchMapping("/order/cart/plusProduct")
	public ResponseEntity<Integer> plusCartProduct() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer productCnt = cartService.plusCnt(memberId, pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt);
		
	}
	
	@PatchMapping("/order/cart/minusProduct")
	public ResponseEntity<Integer> minusCartProduct() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer productCnt = cartService.minusCnt(memberId, pno);
		if(productCnt<=0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(productCnt);
		}
		return ResponseEntity.ok(productCnt); 
	}
	
}
