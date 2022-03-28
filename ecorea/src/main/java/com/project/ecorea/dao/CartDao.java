package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface CartDao {
	
	public List<CartDto.CartList> findByMemberId(String memberId);
	
}
