package com.project.ecorea.entity;

import java.time.LocalDate;

import com.project.ecorea.dto.CorpDto;

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
public class Corp {
	private String id; /* 아이디 */
	private String corpName; /* 기업명 */
	private String pw; /* 비밀번호 */
	private String corpNum; /* 사업자번호 */
	private String email; /* 이메일 */
	private String zipcode; /* 우편번호 */
	private String address1; /* 주소 */
	private String address2; /* 상세주소 */
	private LocalDate joinday; /* 가입날짜 */
	private Boolean enable; /* 아이디 활성화 여부 */
	private String authority; /* 권한 */
	private Integer failcnt; /* 로그인 실패 횟수 */
	
	// Entity 를 CorpDto.Info Dto로 변환
	public CorpDto.Info toInfo() {
		return CorpDto.Info.builder().id(id).corpName(corpName).corpNum(corpNum).email(email).zipcode(zipcode).address1(address1).address2(address2).build();
	}
}