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
	private String id;
	private String corpName;
	private String pw;
	private String corpNum;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private LocalDate joinday;
	private Boolean enable;
	private String authority;
	private Integer failcnt;
	
	public CorpDto.Info toInfo() {
		return CorpDto.Info.builder().id(id).corpName(corpName).corpNum(corpNum).email(email).zipcode(zipcode).address1(address1).address2(address2).build();
	}
}