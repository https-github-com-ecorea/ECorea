package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;

@Mapper
public interface QnaDao {
	
	/* 상품 상세 : 문의 목록 */
	public List<QnaDto.QuestionDto> questionFindByPno(Integer pno);
	
	/* 상품 상세 : 문의 답변 목록 */
	public List<QnaDto.AnswerDto> answerFindByPno(Integer pno);
	
	/* 상품 상세 : 문의 작성 */
	public void questionUpload(QnaQ question);
	
	/* 답변 여부 */
	public int isAnswer(Integer qqno);
	
	/* 일반 회원 마이페이지 : 문의 목록 */
	public List<QnaDto.QuestionDto> memberQuestionFindById(String loginId);
	
	/* 기업 회원 마이페이지 : 문의 목록 */
	public List<QnaDto.QuestionDto> corpQuestionFindById(String loginId);
	
	/* 일반 회원 마이페이지 : 문의 상세 */
	public QnaDto.QuestionDto memberQuestionFindByQqno(String loginId, Integer qqno);
	
	/* 일반 회원 마이페이지 : 문의 상세 답변 */
	public QnaDto.AnswerDto memberAnswerFindByQqno(String loginId, Integer qqno);

	/* 기업 회원 마이페이지 : 문의 상세 */
	public QnaDto.QuestionDto corpQuestionFindByQqno(String loginId, Integer qqno);

	/* 기업 회원 마이페이지 : 문의 상세 답변 */
	public QnaDto.AnswerDto corpAnswerFindByQqno(String loginId, Integer qqno);
	
}
