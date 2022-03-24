package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HugiService {

	private HugiDao dao;
	
	/* 후기 목록 출력 */
	public List<HugiDto.hugiList> hugiList(Integer pno) {
		List<HugiDto.hugiList> dto = new ArrayList<>();
		List<Hugi> entity = dao.findByPno(pno);
		for (Hugi hugi : entity) {
			dto.add(hugi.toDto());
		}
		return dto;
	}
	
}
