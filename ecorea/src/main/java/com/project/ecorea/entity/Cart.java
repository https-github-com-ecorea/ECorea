package com.project.ecorea.entity;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {	
	private String memberId;
	private Integer pno;
	private Integer cartcnt;
	private Integer cartprice;
	private String cartpname;
}
