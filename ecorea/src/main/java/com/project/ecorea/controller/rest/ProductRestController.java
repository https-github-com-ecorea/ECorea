package com.project.ecorea.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.dto.Criteria;
import com.project.ecorea.dto.PagingHugiDto;
import com.project.ecorea.dto.PagingQnaDto;
import com.project.ecorea.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

	private final ProductService productService;
	private final QnaService qnaService;
	private final HugiService hugiService;

	/* 재고 확인 (수량 변경 시 필요) */
	@GetMapping("/product/productList/checkstock")
	public ResponseEntity<Boolean> checkStock(Integer pno, Integer count) {
		Boolean stock = productService.checkStock(pno, count);
		if (stock == false)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(stock);
		return ResponseEntity.ok(stock);
	}
	
	/* 상품 상세 - 후기 목록 (페이징) */
	@GetMapping(value="/productDetail/hugilist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingHugiDto productDetailHugiList(Criteria cri) {
		return hugiService.productDetailHugiList(cri);
	}
	
	/* 상품 상세 - 문의 목록 (페이징) */
	@GetMapping(value="/productDetail/qnalist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingQnaDto productDetailQnaList(Criteria cri) {
		return qnaService.productDetailQuestionList(cri);
	}
	
}
