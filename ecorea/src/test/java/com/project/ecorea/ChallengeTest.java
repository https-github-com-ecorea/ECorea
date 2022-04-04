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
	public boolean DateTest() throws ParseException {
		/* 오늘 날짜가 정해진 기한 안에 들어가는지 확인하는 코드 */
		boolean result = false;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nowTime = new Date();
		String today = format.format(nowTime);
		System.out.println("현재 날짜 : " + today);
		
		String startday = "2022-04-03";
		String endday = "2022-04-13";
		
		Date start = format.parse(startday);
		Date end = format.parse(endday);
		Date to = format.parse(today);
		
		int compare1 = to.compareTo(start);
		int compare2 = end.compareTo(to);
		
		if (compare1 >= 0 && compare2 >= 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	
}
