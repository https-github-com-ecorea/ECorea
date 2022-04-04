package com.project.ecorea.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.ecorea.entity.*;

@Mapper
public interface ChallengeDao {

	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(Challenge challengeDto);

}
