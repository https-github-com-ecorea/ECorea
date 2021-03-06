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

import com.project.ecorea.controller.rest.*;
import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {

	/* Property 읽어 오기 (경로 및 파일) */
	@Value("${upload.image.path}")
	private String imagePath;
	@Value("${upload.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
	private final ChallengeDao dao;
	private final ChProveDao proveDao;
	
	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(ChallengeDto.ChallengeUpload challenge, String loginId) {
		Challenge challengeDto = challenge.toEntity();
		MultipartFile cthumbnail = challenge.getCthumbnail();
		challengeDto.setCorpId(loginId);
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

	/* 기업 회원 : 챌린지 수정 화면 */
	public Challenge challengeUpdateView(Integer cno) {
		Challenge update = dao.challengeUpdateView(cno);
		update.setCthumbnail(imagePath + update.getCthumbnail());
		return update;
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
	public Boolean challengeUpdate(ChallengeDto.ChallengeUpload challengeDto, Integer cno) {
		boolean result;
		Challenge challenge = challengeDto.toEntity();
		MultipartFile cthumbnail = challengeDto.getCthumbnail();
		challenge.setCno(cno);
		if (cthumbnail != null && cthumbnail.isEmpty() == false) {
			String imgname = UUID.randomUUID() + "-" + cthumbnail.getOriginalFilename();
			File file = new File(imageFolder, imgname);
			try {
				cthumbnail.transferTo(file);
				challenge.setCthumbnail(imgname);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		Integer update = dao.challengeUpdate(challenge);
		if (update <= 0)
			result = false;
		result = true;
		return result;
	}
	
	/* 전체 회원 : 챌린지 목록 출력 */
	public List<ChallengeDto.ChallengeList> readchallengeList(Criteria cri) {
		List<ChallengeDto.ChallengeList> list = dao.challengePagingList(cri);
		for(ChallengeDto.ChallengeList lists : list) {
			lists.setCthumbnail(imagePath + lists.getCthumbnail());
		}
		
		return list;
	}

	/* 기업 회원 : 챌린지 목록 출력*/ 
	public List<Challenge> readCorpChallengeList(String loginId, Criteria cri) {
		List<Challenge> corpList = new ArrayList<>();
		List<Challenge> list = dao.challengePagingCorpList(cri);
		for(Challenge lists : list) {
			if(lists.getCorpId().equals(loginId)) {
				lists.setCthumbnail(imagePath + lists.getCthumbnail());
				corpList.add(lists);
			}
		}

		return corpList;
	}

	/* 전체 회원 : 챌린지 상세 페이지 출력 */
	public ChallengeDto.ChallengeDetail readUserDetail(Integer cno) {
		ChallengeDto.ChallengeDetail detail = dao.findByCno(cno);
		detail.setCthumbnail(imagePath + detail.getCthumbnail());
		
		return detail;
	}

	public int getListTotal() {
		return dao.getListTotal();
	}

	public int getCorpListTotal(String loginId) {
		return dao.getCorpListTotal(loginId);
	}

	public Boolean deleteChallenge(Integer cno) {
		return dao.deleteByCno(cno);
	}
	
	
}
