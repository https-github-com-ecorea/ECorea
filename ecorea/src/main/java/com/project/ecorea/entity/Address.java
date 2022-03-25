package com.project.ecorea.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Builder
public class Address {
	
	private Integer ano; /* 배송지 번호 */
	private String aname; /* 배송지명 */
	private String azipcode; /* 우편번호 */
	private String aaddress1; /* 도로명 주소 */
	private String aaddress2; /* 상세 주소 */
	private String atel; /* 전화번호 */
	private String atoname; /* 받는 사람 */
	private Boolean astandard; /* 기본 배송지 여부 */
	private String memberId; /* 회원 아이디 */
	
}
