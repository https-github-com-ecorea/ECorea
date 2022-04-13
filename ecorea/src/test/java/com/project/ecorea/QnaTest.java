package com.project.ecorea;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.QnaDto;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;
import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
public class QnaTest {

	/* Property 읽어 오기 */
	@Value("${product.image.path}") /* 경로 */
	private String imagePath;
	
	@Autowired
	private QnaDao dao;
	@Autowired
	private QnaService service;
	
	// @Test
	public void qnaListTest() {
		// System.out.println(service.productQuestionList(10, "imagepath"));
		// System.out.println(service.productAnswerList(10));
		// System.out.println(service.memberMyPageQuestionList("ngoley6"));
		// System.out.println(service.corpMyPageQuestionList("LG"));
		// System.out.println(service.memberMypageDetail("ngoley6", 3, "이미지 경로 + "));
		// System.out.println(service.corpMypageDetail("LG", 3));
	}
	
	// @Test
	public void uploadQuestionTest() {
		QnaQ question = QnaQ.builder().pno(10).qqno(10).qqcategory("상품").qqtitle("즈기요 .")
			.qqcontent("이거 불량이에요......").qqimg("image").qqregday(LocalDate.now()).memberId("nlitt9").build();
		dao.uploadQuestion(question);
	}
	
	// @Test
	public void uploadAnswerTest() {
		QnaA answer = QnaA.builder().pno(10).qqno(10).qano(6)
				.qacontent("죄송합니다 ㅠㅠ 근데 어디가 불량인지 알려 주셔야죠......").corpId("LG").build();
		dao.uploadAnswer(answer);
	}

	// @Test
	public void updateQuestionTest() {
		QnaQ question = QnaQ.builder().pno(1).qqno(2).qqcategory("상품").qqtitle("즈기요 .")
			.qqcontent("이거 불량이에요...... 아니 수정 전이엇다고요!!").qqimg("image").qqregday(LocalDate.now()).memberId("ngoley6").build();
		Integer result = dao.updateQuestion(question);
		System.out.println("결과 : " + result);
	}
	
	// @Test
	public void updateAnswerTest() {
		QnaA answer = QnaA.builder().pno(3).qqno(1).qano(1)
				.qacontent("네 수정 시간을 드리겠읍니다......").corpId("SAMSUNG").build();
		Integer result = dao.updateAnswer(answer);
		System.out.println("결과 : " + result);
	}
	
	// @Test
	public void deleteQuestionTest() {
		Integer result = dao.deleteQuestion("ngoley6", 2);
		System.out.println("결과 : " + result);
	}
	
	// @Test
	public void deleteAnswerTest() {
		Integer result = dao.deleteAnswer("LG", 2);
		System.out.println("결과 : " + result);
	}
	
}
