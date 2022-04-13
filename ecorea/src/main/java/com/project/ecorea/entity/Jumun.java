package com.project.ecorea.entity;

import java.time.*;

import com.project.ecorea.dto.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Jumun {
	private Integer jno;
	private Integer pno;
	private Integer jCnt;
	private ShippingStatus jStatus;
	private Integer jPrice;
	private Integer jUsePoint;
	private LocalDate jDay;
	private String memberId;
	private Integer ano;
	private String jShippingMsg;
	
	public JumunDto.JumunList toJumunList() {
		return JumunDto.JumunList.builder().jno(jno).pno(pno).pname(null).pthumbnail(null)
				.price(jPrice).cnt(jCnt).jstatus(jStatus.getKorean()).build();
	}
}
