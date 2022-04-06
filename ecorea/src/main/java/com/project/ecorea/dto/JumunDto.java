package com.project.ecorea.dto;

import java.util.*;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JumunDto {
	@Data
	@AllArgsConstructor
	public static class Params {
		private Integer pno;
		private Integer cnt;
	}
	
	private List<Params> list;
	
	@Data
	public static class JumunProduct {
		private Integer jno;
		private Integer pno;
		private String pname;
		private String pthumbnail;
		private Integer price;
		private Integer cnt;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	public static class JumunSheet {
		// 주문 상품 정보
		private List<JumunProduct> products;
		
		// 결제정보
		private Integer usePoint;
		private Integer totalPrice;
		
		// 주문자
		private String orderer;
		private String email;
		private String oTel;
		
		// 배송 정보
		private String recipient;
		private String rTel;
		private String addressName;
		private String address;
		private String shippingMsg;		
	}

}
