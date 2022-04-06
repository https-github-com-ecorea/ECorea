package com.project.ecorea.entity;

import java.time.*;

import org.springframework.format.annotation.*;

import com.project.ecorea.dto.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ChProve {
	private Integer cpno;	// 챌린지 인증 번호
	private Integer cno;	// 챌린지 번호
	private String cptitle;
	private String cpcontent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cpregday;
	private String cpimg;
	private String memberId;
	
}
