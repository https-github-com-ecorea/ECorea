package com.project.ecorea.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.project.ecorea.entity.Corp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CorpDto {
	@Data
	@Builder
	public static class Join {
		@NotNull
		/* @Pattern(regexp = "^[a-z0-9]{5,10}$", message = "아이디는 소문자나 숫자 5~20자 입니다") */
		private String id;
		@NotNull
		/* @Pattern(regexp = "^[가-힣a-zA-Z]{2,20}$") */
		private String corpName;
		@NotNull
		/* @Pattern(regexp = "(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$") */
		private String pw;
		private String corpNum;
		@NotNull
		/*
		 * @Pattern(regexp =
		 * "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
		 */
		private String email;
		private String zipcode;
		private String address1;
		private String address2;
		
		public Corp toEntity() {
			return Corp.builder().id(id).corpName(corpName).corpNum(corpNum).email(email).zipcode(zipcode).address1(address1).address2(address2).build();
		}
	}
	
	@Data
	public static class infoUpdate {
		@Pattern(regexp = "(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$")
		private String pw;

		public Corp toEntity() {
			Corp corp= new Corp();
			if(pw.equals("")==false)
				corp.setPw(pw);
			return corp;
		}
	}
	
	@Data
	@Builder
	public static class Info {
		private String id;
		private String corpName;
		private String corpNum;
		private String email;
		private String zipcode;
		private String address1;
		private String address2;
	}
	
}
