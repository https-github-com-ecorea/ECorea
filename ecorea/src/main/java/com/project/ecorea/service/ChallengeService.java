package com.project.ecorea.service;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {

	/* Property 읽어 오기 (경로 및 파일) */
	@Value("${product.image.path}")
	private String imagePath;
	@Value("${product.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
	private final ChallengeDao dao;
	
	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(ChallengeDto.challengeUpload challenge) {
		Challenge challengeDto = challenge.toEntity();
		MultipartFile cthumbnail = challenge.getCthumbnail();
		challengeDto.setCthumbnail(defaultImage);
		if (cthumbnail != null && cthumbnail.isEmpty() == false && cthumbnail.getContentType().toLowerCase().startsWith("image/")) {
			String imgname = UUID.randomUUID() + "-" + cthumbnail.getOriginalFilename();
			File file = new File(imageFolder, imgname);
			try {
				cthumbnail.transferTo(file);
				challengeDto.setCthumbnail(imgname);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		dao.challengeUpload(challengeDto);
	}

	/* 기업 회원 : 챌린지 수정 가능 날짜 확인 */
	public boolean challengeUpdateisDate(Challenge challenge) {
		LocalDate localRegday = challenge.getCregday();
		Date regday = java.sql.Date.valueOf(localRegday);
		Calendar cal = Calendar.getInstance();
		cal.setTime(regday);
		cal.add(Calendar.DATE, 10);
		Date endday = cal.getTime();
		
		boolean result = false;
		Date today = new Date();
		int compare1 = today.compareTo(regday);
		int compare2 = endday.compareTo(today);
		if (compare1 >= 0 && compare2 >= 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	/* 기업 회원 : 챌린지 수정 */
	public Boolean challengeUpdate(Challenge challenge) {
		boolean result;
		if (challengeUpdateisDate(challenge) == true) {
			Integer update = dao.challengeUpdate(challenge);
			if (update <= 0)
				result = false;
			result = true;		
		} else {
			result = false;
		}
		return result;
	}
	
	public List<Challenge> readchallengeList() {
		List<Challenge> challenge = dao.findByChallengeAll();
		
		return challenge;
	}

	public List<Challenge> readCorpChallengeList(String loginId) {
		List<Challenge> challenge = dao.findByCorpId(loginId);
		return challenge;
	}

	public ChallengeDto.ChallengeDetail readUserDetail(Integer cno) {
		Challenge challenge = dao.findBycno(cno);
		ChallengeDto.ChallengeDetail detail = challenge.toDto();
		Integer applyCnt = (challenge.getCjoincnt() / challenge.getCgoal()) * 100;
		
		detail.setApplycnt(applyCnt);
		
		return detail;
	}

	public ChallengeDto.ChallengeDetail readCorpDetail(Integer cno) {
		Challenge challenge = dao.findBycno(cno);
		ChallengeDto.ChallengeDetail detail = challenge.toDto();
		Integer applyCnt = (challenge.getCjoincnt() / challenge.getCgoal()) * 100;
		
		detail.setApplycnt(applyCnt);
		
		return detail;
	}

}
