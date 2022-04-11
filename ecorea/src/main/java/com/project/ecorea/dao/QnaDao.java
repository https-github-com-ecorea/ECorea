package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;

@Mapper
public interface QnaDao {
	
	/* 문의 개수 */
	public int getTotal();
	
	/* 일반 회원 문의 개수 */
	public int getMemberTotal(String loginId);
	
	/* 기업 회원 문의 개수 */
	public int getCorpTotal(String loginId);
	
	/* 상품 상세 : 문의 목록 */
	public List<QnaDto.QuestionDto> questionFindByPno(Integer pno);
	
	/* 상품 상세 : 문의 답변 목록 */
	public List<QnaDto.AnswerDto> answerFindByPno(Integer pno);
	
	/* 상품 상세 : 문의 작성 */
	public void uploadQuestion(QnaQ question);
	
	/* 답변 여부 */
	public int isAnswer(Integer qqno);
	
	/* 일반 회원 마이페이지 : 문의 목록 */
	public List<QnaDto.QuestionDto> memberQuestionFindById(@Param("loginId")String loginId, @Param("cri")Criteria cri);
	
	/* 기업 회원 마이페이지 : 문의 목록 */
	public List<QnaDto.QuestionDto> corpQuestionFindById(@Param("loginId")String loginId, @Param("cri")Criteria cri);
	
	/* 일반 회원 마이페이지 : 문의 상세 */
	public QnaDto.QuestionDto memberQuestionFindByQqno(String loginId, Integer qqno);
	
	/* 일반 회원 마이페이지 : 문의 상세 답변 */
	public QnaDto.AnswerDto memberAnswerFindByQqno(String loginId, Integer qqno);
	
	/* 일반 회원 마이페이지 : 문의 수정 */
	public Integer updateQuestion(QnaQ question);
	
	/* 일반 회원 마이페이지 : 문의 삭제 */
	public Integer deleteQuestion(String memberId, Integer qqno);
	
	/* 기업 회원 마이페이지 : 문의 상세 */
	public QnaDto.QuestionDto corpQuestionFindByQqno(String loginId, Integer qqno);

	/* 기업 회원 마이페이지 : 문의 상세 답변 */
	public QnaDto.AnswerDto corpAnswerFindByQqno(String loginId, Integer qqno);
	
	/* 기업 회원 마이페이지 : 문의 상세 답변 작성 */
	public void uploadAnswer(QnaA answer);
	
	/* 기업 회원 마이페이지 : 문의 답변 수정 */
	public Integer updateAnswer(QnaA answer);

	/* 기업 회원 마이페이지 : 문의 답변 삭제 */
	public Integer deleteAnswer(String corpId, Integer qano);
	
}
