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
	public List<QnaDto.QnaQList> qnaQList(Integer pno) {
		List<QnaDto.QnaQList> dto = new ArrayList<>();
		List<QnaDto.QnaQList> entity = dao.qfindByPno(pno);
		for (QnaDto.QnaQList qna : entity) {
			dto.add(qna);
		}
		return dto;
	}
	
	/* 문의 답변 목록 출력 */
	public List<QnaDto.QnaAList> qnaAList(Integer pno) {
		List<QnaDto.QnaAList> dto = new ArrayList<>();
		List<QnaDto.QnaAList> entity = dao.afindByPno(pno);
		for (QnaDto.QnaAList qna : entity) {
			dto.add(qna);
		}
		return dto;
	}

}
