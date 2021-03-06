package com.project.ecorea.dto;

import java.util.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
	
	@Data
	@Builder
	public static class Join {
		@NotNull
		@Pattern(regexp = "^[a-z0-9]{8,20}$", message = "아이디는 소문자나 숫자 8~20자 입니다")
		private String id;
		@NotNull
		@Pattern(regexp = "(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$")
		private String pw;
		@NotNull
		@Pattern(regexp = "^[가-힣]{2,20}$")
		private String name;
		@NotNull
		@Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
		private String email;
		
		public Member toEntity() {
			return Member.builder().id(id).pw(pw).name(name).email(email).build();
		}
	}
	
	@Data
	public static class InfoUpdate {
		// 알파벳 소문자,대문자,숫자,특수문자를 포함한 8-10글자 단 특수문자가 1개이상은 무조건 들어가야한다
		@Pattern(regexp = "(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$")
		private String pw;

		public Member toEntity() {
			Member member = new Member();
			if(pw.equals("")==false)
				member.setPw(pw);
			return member;
		}
	}
	
	@Data
	@Builder
	public static class Info {
		private String id;
		private String name;
		private String email;
	}
	
	@Data
	@AllArgsConstructor
	public static class Mypage {
		private Integer point;
		private Integer payCnt;
		private Integer shippingCnt;
		private Integer completeCnt;
		private Integer cancelCnt;
	}
}
