package com.project.ecorea.service;

import java.security.*;
import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.HugiDto.*;
import com.project.ecorea.entity.*;

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
	
	/* 일반 회원 후기 목록 출력 */
	public List<HugiDto.HugiList> memberHugiList(String loginId) {
		List<HugiDto.HugiList> memberHugis = dao.findByhwriter(loginId);
		
		return memberHugis;
	}

	/* 일반 회원 후기 삭제 */
	public void reviewDelete(Integer hno) {
		dao.deleteByHno(hno);
	}

	/* 일반 회원 상품 등록 */
	public void reviewUpload(String loginId, HugiUpload upload) {
		Hugi hugi = upload.toEntity();
		hugi.setHwriter(loginId).setHregday(LocalDate.now());
		
		dao.reviewSave(hugi);
	}
	
	
}