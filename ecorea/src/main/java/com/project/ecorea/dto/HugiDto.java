package com.project.ecorea.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HugiDto {
	
	/* 후기 출력용 */
	@Data
	@AllArgsConstructor
	public static class HugiList {
		private Integer hno; /* 후기 번호 */
		private String htitle; /* 제목 */
		private String hcontent; /* 내용 */
		private String hwriter; /* 작성자 */
		private String hregday; /* 등록일 */
		private String himg; /* 이미지 */
	}

}
