package com.project.ecorea.dto;

import java.time.format.*;
import java.util.*;

import com.project.ecorea.dto.CartDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JumunDto {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Params {
		private Integer pno;
		private Integer cnt;
	}
	
	@Data
	public static class ParamsList {
		private List<Params> paramsList;
	}	
	
	
	@Data
	@AllArgsConstructor
	public static class JumunPreview {
		private List<CartProduct> products;
		private Integer havePoint;
		private Integer totalPrice;
		private String memberName;
		private String memberEmail;
	}	
	
	
	@Data
	@AllArgsConstructor
	public static class JumunProduct {
		private Integer jno;
		private Integer pno;
		private String pname;
		private String pthumbnail;
		private Integer price;
		private Integer cnt;
	}
	
	@Data
	@AllArgsConstructor
	public static class JumunInput {
		private Integer addressNo;
		private Integer usePoint;
		private String shippingMsg;		
	}
	
	
	@Data
	@AllArgsConstructor
	@Builder
	public static class JumunList {
		private Integer jno;
		private Integer pno;
		private String pname;
		private String pthumbnail;
		private Integer price;
		private Integer cnt;
		private String jstatus;
	}
	
	@Data
	@AllArgsConstructor
	public static class JumunStatus {
		private String jstatus;
		private Integer statusCnt;
	}

	/*
	
	@Data
	public static class JumunMemberInfo {
		// 결제정보
		private Integer havePoint;
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
				
	}

	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class JumunSheet {
		// 주문 상품 정보
		private List<JumunProduct> products;
		
		// 주문자, 배송 정보
		private JumunMemberInfo memberInfo;	
		private String shippingMsg;
	}
	
	*/
	

}
