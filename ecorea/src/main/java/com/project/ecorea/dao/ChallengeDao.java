package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.entity.*;

@Mapper
public interface ChallengeDao {
	public List<Challenge> findByChallengeAll();

	public List<Challenge> findByCorpId(String id);

	public Challenge findBycno(Integer cno);

}
