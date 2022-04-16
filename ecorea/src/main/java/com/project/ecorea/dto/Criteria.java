package com.project.ecorea.dto;

import lombok.Data;

@Data
public class Criteria {
	
	private int nowPage; /* 현재 페이지 */
	private int amount; /* 한 페이지당 보여질 게시물 개수 */
	private String catecode; /* 카테고리 */
	private String sort; /* 정렬 */
	
	private int pno; /* 상품 번호 (상품 상세 페이지에서 사용) */
	
	/* 파라미터 없이 호출했을 때 (기본 값) */
	public Criteria() {
		this.nowPage = 1;
		this.amount = 9;
	}
	
	/* 파라미터 두 개 */
	public Criteria(int nowPage, int amount) {
		this.nowPage = nowPage;
		this.amount = amount;
	}
	
	/* 파라미터 넣고 호출했을 때 */
	public Criteria(int nowPage, int amount, String catecode, String sort) {
		this.nowPage = nowPage;
		this.amount = amount;
		this.catecode = catecode;
		this.sort = sort;
	}
	
}
