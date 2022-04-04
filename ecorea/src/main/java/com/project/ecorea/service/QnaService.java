package com.project.ecorea.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;

import lombok.*;

@Service
@RequiredArgsConstructor
public class QnaService {
	
	/* Property 읽어 오기 (경로 및 파일) */
	@Value("${product.image.path}")
	private String imagePath;
	@Value("${product.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
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
				qna.setIsAnswer("O");
			} else {
				qna.setIsAnswer("X");
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
				qna.setIsAnswer("O");
			} else {
				qna.setIsAnswer("X");
			}
			dto.add(qna);
		}
		return dto;
	}
	
	/* 일반 회원 : 문의 상세 */
	public Object memberMypageDetail(String loginId, Integer qqno, String imagepath) {
		QnaDto.QuestionDto question = dao.memberQuestionFindByQqno(loginId, qqno);
		QnaDto.AnswerDto answer = dao.memberAnswerFindByQqno(loginId, qqno);
		// HashSet<Object> qna = new HashSet<>();
		List<Object> qna = new ArrayList<>();
		question.setQqimg((imagepath + question.getQqimg()));
		qna.add(question);
		/* 
		if (answer != null)
			qna.add(answer);
		*/
		qna.add(answer);
		return qna;
	}
	
	/* 기업 회원 : 문의 상세 */
	public Object corpMypageDetail(String loginId, Integer qqno, String imagepath) {
		QnaDto.QuestionDto question = dao.corpQuestionFindByQqno(loginId, qqno);
		QnaDto.AnswerDto answer = dao.corpAnswerFindByQqno(loginId, qqno);
		// HashSet<Object> qna = new HashSet<>();
		List<Object> qna = new ArrayList<>();
		question.setQqimg((imagepath + question.getQqimg()));
		qna.add(question);
		qna.add(answer);
		return qna;
	}

	/* 일반 회원 : 문의 작성 */
	public void uploadQuestion(QnaDto.uploadQuestion questionUpDto) {
		QnaQ question = questionUpDto.toEntity();
		MultipartFile qqimg = questionUpDto.getQqimg();
		question.setQqimg(defaultImage);
		if(qqimg!=null && qqimg.isEmpty()==false && qqimg.getContentType().toLowerCase().startsWith("image/")) {
			String qqimgName = UUID.randomUUID() + "-" + qqimg.getOriginalFilename();
			File file = new File(imageFolder, qqimgName);
			try {
				qqimg.transferTo(file);
				question.setQqimg(qqimgName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		dao.uploadQuestion(question);
	}
	
	/* 일반 회원 : 문의 수정 */
	public Boolean updateQuestion(QnaQ questionDto) {
		Integer result = dao.updateQuestion(questionDto);
		if (result <= 0)
			return false;
		return true;
	}

	/* 일반 회원 : 문의 삭제 */
	public Boolean deleteQuestion(String loginId, Integer qqno) {
		Integer result = dao.deleteQuestion(loginId, qqno);
		if (result <= 0)
			return false;
		return true;
	}
	
	/* 기업 회원 : 문의 답변 작성 */
	public void uploadAnswer(QnaDto.AnswerDto answerUpDto) {
		QnaA answer = answerUpDto.toEntity();
		dao.uploadAnswer(answer);
	}
	
	/* 기업 회원 : 문의 답변 수정 */
	public Boolean updateAnswer(QnaA questionDto) {
		Integer result = dao.updateAnswer(questionDto);
		if (result <= 0)
			return false;
		return true;		
	}

	/* 기업 회원 : 문의 답변 삭제 */
	public Boolean deleteAnswer(String loginId, Integer qano) {
		Integer result = dao.deleteAnswer(loginId, qano);
		if (result <= 0)
			return false;
		return true;
	}

}
