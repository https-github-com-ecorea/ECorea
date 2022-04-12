package com.project.ecorea.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.project.ecorea.entity.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
