package com.project.ecorea.entity;

import java.time.LocalDate;

import com.project.ecorea.dto.MemberDto;
import com.project.ecorea.dto.MemberDto.Info;

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
public class Member {
	private String id;
	private String pw;
	private String name;
	private String email;
	private Integer point;
	private String checkcode;
	private LocalDate joinday;
	private Boolean enable;
	private String authority;
	private Integer failcnt;
	
	public Info toInfo() {
		return MemberDto.Info.builder().id(id).name(name).email(email).build();
	}
}