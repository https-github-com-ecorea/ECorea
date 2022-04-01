package com.project.ecorea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
public class QnaTest {

	/* Property 읽어 오기 */
	@Value("${product.image.path}") /* 경로 */
	private String imagePath;
	
	@Autowired
	private QnaService service;
	
	@Test
	public void qnaListTest() {
		// System.out.println(service.productQuestionList(10, "imagepath"));
		// System.out.println(service.productAnswerList(10));
		// System.out.println(service.memberMyPageQuestionList("ngoley6"));
		// System.out.println(service.corpMyPageQuestionList("LG"));
		System.out.println(service.memberMypageDetail("ngoley6", 3, "이미지 경로 + "));
		// System.out.println(service.corpMypageDetail("LG", 3));
	}
	
}
