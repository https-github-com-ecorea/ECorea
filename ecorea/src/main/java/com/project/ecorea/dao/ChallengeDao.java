package com.project.ecorea.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ChallengeDto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface ChallengeDao {

	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(Challenge challenge);
  
	/* 기업 회원 : 챌린지 수정 화면 */
	public Challenge challengeUpdateView(Integer cno);
	
	/* 기업 회원 : 챌린지 수정 */
	public Integer challengeUpdate(Challenge challenge);

	public List<Challenge> findByChallengeAll();

	public List<ChallengeDto.ChallengeList> findByCorpId(String id);

	public ChallengeDto.ChallengeDetail findByCno(Integer cno);

	public List<ChallengeDto.ChallengeList> findByCorpName();

	public List<ChallengeDto.ChallengeList> challengePagingList(Criteria cri);

	public int getListTotal();

	public int getCorpListTotal(String id);

	public List<Challenge> challengePagingCorpList(Criteria cri);

	public Boolean deleteByCno(Integer cno);
	
}
