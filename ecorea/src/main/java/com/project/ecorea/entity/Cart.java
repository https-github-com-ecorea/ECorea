package com.project.ecorea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cart {	
	private String memberId;
	private Integer pno;
	private Integer cartcnt;
	private Integer cartprice;
	private String cartpname;
}
