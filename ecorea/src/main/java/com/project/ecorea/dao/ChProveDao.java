package com.project. ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ChProveDto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface ChProveDao {
	public List<ChProveDto.ProveList> findByMemberId(String memberId);
	
	public void saveChProve(ChProve chProve);

	public List<ChProveDto.ChallengeDetailProveList> findByProveAll();
}
