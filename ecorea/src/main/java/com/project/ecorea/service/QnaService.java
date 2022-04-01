package com.project.ecorea.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class QnaService {
	
	/* Property 읽어 오기 */
	@Value("${product.image.path}") /* 경로 */
	private String imagePath;
	
	private final QnaDao dao;
	
	/* 상품 상세 : 문의 목록 */
	public List<QnaDto.QuestionDto> productQuestionList(Integer pno, String imagepath) {
		List<QnaDto.QuestionDto> dto = new ArrayList<>();
		List<QnaDto.QuestionDto> entity = dao.questionFindByPno(pno);
		for (QnaDto.QuestionDto qna : entity) {
			qna.setQqimg(imagepath + qna.getQqimg());
			dto.add(qna);
		}
		return dto;
	}
	
	/* 상품 상세 : 문의 답변 목록 */
	public List<QnaDto.AnswerDto> productAnswerList(Integer pno) {
		List<QnaDto.AnswerDto> dto = new ArrayList<>();
		List<QnaDto.AnswerDto> entity = dao.answerFindByPno(pno);
		for (QnaDto.AnswerDto qna : entity) {
			dto.add(qna);
		}
		return dto;
	}
	
	/* 일반 회원 : 문의 목록 */
	public List<QnaDto.QuestionDto> memberMyPageQuestionList(String loginId) {
		List<QnaDto.QuestionDto> dto = new ArrayList<>();
		List<QnaDto.QuestionDto> entity = dao.memberQuestionFindById(loginId);
		for (QnaDto.QuestionDto qna : entity) {
			if (dao.isAnswer(qna.getQqno()) > 0) {
				qna.setIsAnswer(true);
			} else {
				qna.setIsAnswer(false);
			}
			dto.add(qna);
		}
		return dto;
	}
	
	/* 기업 회원 : 문의 목록 */
	public List<QnaDto.QuestionDto> corpMyPageQuestionList(String loginId) {
		List<QnaDto.QuestionDto> dto = new ArrayList<>();
		List<QnaDto.QuestionDto> entity = dao.corpQuestionFindById(loginId);
		for (QnaDto.QuestionDto qna : entity) {
			if (dao.isAnswer(qna.getQqno()) > 0) {
				qna.setIsAnswer(true);
			} else {
				qna.setIsAnswer(false);
			}
			dto.add(qna);
		}
		return dto;
	}
	
	/* 일반 회원 : 문의 상세 */
	public Object memberMypageDetail(String loginId, Integer qqno, String imagepath) {
		QnaDto.QuestionDto question = dao.memberQuestionFindByQqno(loginId, qqno);
		QnaDto.AnswerDto answer = dao.memberAnswerFindByQqno(loginId, qqno);
		HashSet<Object> qna = new HashSet<>();
		question.setQqimg((imagepath + question.getQqimg()));
		qna.add(question);
		qna.add(answer);
		return qna;
	}
	
	/* 기업 회원 : 문의 상세 */
	public Object corpMypageDetail(String loginId, Integer qqno, String imagepath) {
		QnaDto.QuestionDto question = dao.corpQuestionFindByQqno(loginId, qqno);
		QnaDto.AnswerDto answer = dao.corpAnswerFindByQqno(loginId, qqno);
		HashSet<Object> qna = new HashSet<>();
		question.setQqimg((imagepath + question.getQqimg()));
		qna.add(question);
		qna.add(answer);
		return qna;
	}

	/* 일반 회원 : 문의 작성 */
	
	
	/* 기업 회원 : 문의 답변 작성 */
	
	
}
