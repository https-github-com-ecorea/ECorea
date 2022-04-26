package com.project. ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ChProveDto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface ChProveDao {
	public List<ChProveDto.ProveList> findByMemberId(String memberId, String imagePath);
	
	public void saveChProve(ChProve chProve);
  
	public List<ChProveDto.ChallengeDetailProveList> findByProveAll(Criteria cri);
	
	public Integer deleteByMemberIdAndCpno(String memberId, Integer cpno);
	
	public Integer deleteByMemberIdAndCno(String memberId, Integer cno);

	public int getChallengeDetailTotal(Integer cno);

	public void saveApply(ChApplyCheck chApplyCheck);

}
