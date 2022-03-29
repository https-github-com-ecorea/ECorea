package com.project.ecorea.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartDto {
	
	@Data
	public static class CartProduct {
		private Integer pno;
		private String cartpname;
		private String pthumbnail;
		private Integer cartcnt;
		private Integer price;		
	}
	
	@Data
	public static class CartList {
		private String corpName;		
		private Integer cartprice;
		private List<CartProduct> cartProduct;
	}
		
	private List<CartList> cartList;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class DeleteSelected {
		private List<Integer> pnos;
	}

}
