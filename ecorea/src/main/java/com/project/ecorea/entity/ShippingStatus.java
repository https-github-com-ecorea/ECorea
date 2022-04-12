package com.project.ecorea.entity;

import lombok.*;

public enum ShippingStatus {
	PAY("결제완료"), SHIPPING("배송중"), CANCEL("취소"), RETURN("환불"), COMPLETE("배송완료"); 

	@Getter
	private String korean;
	
	ShippingStatus(String kor) {
		this.korean = kor;
	}
}
