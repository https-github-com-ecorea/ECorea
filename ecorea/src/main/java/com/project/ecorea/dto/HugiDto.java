package com.project.ecorea.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.*;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HugiDto {
	
	/* 후기 출력용 */
	@Data
	public static class HugiList {
		private Integer hno; /* 후기 번호 */
		private String htitle; /* 제목 */
		private String hcontent; /* 내용 */
		private String hwriter; /* 작성자 */
		private LocalDate hregday; /* 등록일 */
		private String himg; /* 이미지 */
	}
	
	/* 후기 등록 */
	@Data
	public static class HugiUpload {
		private Integer jno;
		private String htitle;
		private String hcontent;
		
		private MultipartFile himg;
		
		public Hugi toEntity() {
			return Hugi.builder().htitle(htitle).hcontent(hcontent).jno(jno).build();
		}
	}
	
	/* 후기 변경 */
	@Data
	public static class HugiUpdate {
		private Integer hno;
		private String htitle;
		private String hcontent;
		private MultipartFile himg;
		
		public Hugi toEntity() {
			return Hugi.builder().hno(hno).htitle(htitle).hcontent(hcontent).build();
		}
	}

}
