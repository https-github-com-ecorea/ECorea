package com.project.ecorea.entity;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {	
	private String memberId;
	private Integer pno;
	private Integer cartcnt; /* 장바구니에 담은 수량 */
	/* 일단은 이거 세 개만 사용하는 걸로 하고 넘기기! */
	
	private Integer cartprice;
	private String cartpname;
}
