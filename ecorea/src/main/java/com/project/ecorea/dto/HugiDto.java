package com.project.ecorea.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
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
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate hregday; /* 등록일 */
		private String himg; /* 이미지 */
	}
	
	/* 후기 등록 */
	@Data
	public static class HugiUpload {
		private Integer jno; /* 주문 번호 */
		private String htitle; /* 후기 제목 */
		private String hcontent; /* 후기 내용 */
		
		private MultipartFile himg; /* 후기 이미지 */
		
		/* Entity 로 변환하는 Method */
		public Hugi toEntity() {
			return Hugi.builder().htitle(htitle).hcontent(hcontent).jno(1).build();
		}
	}
	
	/* 후기 변경 */
	@Data
	public static class HugiUpdate {
		private Integer hno; /* 후기 번호 */
		private String htitle; /* 후기 제목 */
		private String hcontent; /* 후기 내용 */
		private MultipartFile himg; /* 후기 이미지 */
		
		/* Entity로 변환하는 Method */
		public Hugi toEntity() {
			return Hugi.builder().hno(hno).htitle(htitle).hcontent(hcontent).build();
		}
	}
}
