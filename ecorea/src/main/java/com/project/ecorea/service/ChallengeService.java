package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@AllArgsConstructor
@Service
public class ChallengeService {
	private ChallengeDao challengeDao;
	
	public List<Challenge> readchallengeList() {
		List<Challenge> challenge = challengeDao.findByChallengeAll();
		
		return challenge;
	}

	public List<Challenge> readCorpChallengeList(String loginId) {
		List<Challenge> challenge = challengeDao.findByCorpId(loginId);
		return challenge;
	}

	public ChallengeDto.ChallengeDetail readUserDetail(Integer cno) {
		Challenge challenge = challengeDao.findBycno(cno);
		ChallengeDto.ChallengeDetail detail = challenge.toDto();
		Integer applyCnt = (challenge.getCjoincnt() / challenge.getCgoal()) * 100;
		
		detail.setApplycnt(applyCnt);
		
		return detail;
	}

	public ChallengeDto.ChallengeDetail readCorpDetail(Integer cno) {
		Challenge challenge = challengeDao.findBycno(cno);
		ChallengeDto.ChallengeDetail detail = challenge.toDto();
		Integer applyCnt = (challenge.getCjoincnt() / challenge.getCgoal()) * 100;
		
		detail.setApplycnt(applyCnt);
		
		return detail;
	}
}
