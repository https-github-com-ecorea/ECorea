package com.project.ecorea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.ecorea.dao.ChallengeDao;
import com.project.ecorea.entity.Challenge;
import com.project.ecorea.service.ChallengeService;

@SpringBootTest
public class ChallengeTest {

	@Autowired
	private ChallengeService service;
	@Autowired
	private ChallengeDao dao;
	
	// @Test
	public void ChallengeUploadTest() {
		Challenge clg = Challenge.builder().cno(1).cname("LG").cgoal(50).cpoint(2000).cregday(LocalDate.now())
				.cstartday(LocalDate.of(2022, 4, 3)).cendday(LocalDate.of(2022, 5, 3)).corpId("LG")
				.cthumbnail("imgimgimgimg").cjoincnt(0).ccontent("환경 보호!!").build();
		dao.challengeUpload(clg);
	}
	
	@Test
	public void DateTest() {
		LocalDate date = LocalDate.of(2022, 3, 26);
		Challenge clg = Challenge.builder().cno(22).cname("LG").cgoal(50).cpoint(2000).cregday(date)
				.cstartday(LocalDate.of(2022, 4, 3)).cendday(LocalDate.of(2022, 5, 3)).corpId("LG")
				.cthumbnail("imgimgimgimg").cjoincnt(0).ccontent("환경 보호!! 수정 수정ㅠㅠ").build();
		System.out.println("결과 : " + service.challengeUpdate(clg));
	}
 	
}
