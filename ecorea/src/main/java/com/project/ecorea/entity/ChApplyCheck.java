package com.project.ecorea.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class ChApplyCheck {
	private Integer chapplyno;
	private String memberId;
	private Integer cno;
}
