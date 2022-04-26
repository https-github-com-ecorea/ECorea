package com.project.ecorea.dao;


import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;


@Mapper
public interface JumunDao {

	public Integer saveJumun(Jumun jumun);

	public List<JumunDto.JumunList> findByMemberId(String memberId, String imagePath);
	
	public List<Jumun> jumunFindByMemberId(String memberId);
		
	public JumunDto.JumunList jumunFindByMemberIdAndJno(String memberId, String imagePath, Integer jno);
	
	public List<JumunDto.JumunStatus> countShippingStatus(String memberId);
}
