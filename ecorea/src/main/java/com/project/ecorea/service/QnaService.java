package com.project.ecorea.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.*;

@Service
@AllArgsConstructor
public class QnaService {
	
	private QnaDao dao;
	
	/* 문의 목록 출력 */
	public List<QnaDto.QnaList> qnaList(Integer pno) {
		List<QnaDto.QnaList> dto = new ArrayList<>();
		List<QnaDto.QnaList> entity = dao.findByPno(pno);
		for (QnaDto.QnaList qna : entity) {
			dto.add(qna);
		}
		return dto;
	}
	
}
