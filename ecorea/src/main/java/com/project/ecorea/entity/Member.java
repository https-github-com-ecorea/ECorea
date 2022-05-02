package com.project.ecorea.entity;

import java.time.LocalDate;


import com.project.ecorea.dto.MemberDto;
import com.project.ecorea.dto.MemberDto.Info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Member {
	private String id; /* 아이디 */
	private String pw; /* 비밀번호 */
	private String name; /* 이름 */
	private String email; /* 이메일 */
	private Integer point; /* 포인트 */
	private String checkcode; /* 체크코드 */
	private LocalDate joinday; /* 가입날짜 */
	private Boolean enable; /* 계정 활성화 여부 */
	private String authority; /* 권한 */
	private Integer failcnt; /* 로그인 실패 횟수 */
	
	// Entity 를 MemberDto.Info Dto로 변환
	public Info toInfo() {
		return MemberDto.Info.builder().id(id).name(name).email(email).build();
	}
}