package com.project.ecorea.dto;

import java.time.*;
import java.util.*;

import org.springframework.format.annotation.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.entity.*;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ChProveDto {
	@Data
	@Builder
	public static class Prove {
		private Integer cpno;
		private String cpimg;
		private String cptitle;
		private String cpregday;
	}
	
	@Data
	public static class ProveList {
		private Integer cno;
		private String cname;
		private List<Prove> proves;				
	}
	
	@Data
	@Builder
	public static class InputProve {
		private Integer cno;
		private String cptitle;
		private String cpcontent;
		private MultipartFile cpimg;
		
		public ChProve toEntity() {
			return ChProve.builder().cno(cno).cptitle(cptitle).cpcontent(cpcontent).build();
		}
	}
	
	@Data
	public static class ChallengeDetailProveList {
		private String cname;
		private Integer cpno;
		private String cpimg;
		private String cptitle;
		private String cpcontent;
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate cpregday;
		private String cpwriter;
	}
}

