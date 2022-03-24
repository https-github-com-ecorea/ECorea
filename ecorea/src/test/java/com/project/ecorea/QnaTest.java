package com.project.ecorea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
public class QnaTest {

	@Autowired
	private QnaService service;
	
	@Test
	public void qnaListTest() {
		System.out.println(service.qnaList(10));
	}
	
}
