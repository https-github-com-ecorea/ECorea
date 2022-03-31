package com.project.ecorea.entity;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Bookmark {
	private String memberId;
	private Integer pno;
}
