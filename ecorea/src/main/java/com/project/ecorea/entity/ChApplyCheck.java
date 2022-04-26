package com.project.ecorea.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ChApplyCheck {
	private Integer chapplynum;
	private String id;
	private Integer cno;
}
