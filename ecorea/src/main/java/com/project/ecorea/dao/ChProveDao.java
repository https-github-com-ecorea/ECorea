package com.project. ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;

@Mapper
public interface ChProveDao {
	public List<ChProveDto.ProveList> findByMemberId(String memberId);
}
