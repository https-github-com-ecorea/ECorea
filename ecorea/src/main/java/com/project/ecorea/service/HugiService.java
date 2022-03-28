package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HugiService {

	private HugiDao dao;
	
	/* 후기 목록 출력 */
	public List<HugiDto.HugiList> hugiList(Integer pno) {
		List<HugiDto.HugiList> hugis = dao.findByPno(pno);
		return hugis;
	}

}
