package com.project.ecorea.dao;


import org.apache.ibatis.annotations.*;

import com.project.ecorea.entity.*;


@Mapper
public interface JumunDao {

	public Integer saveJumun(Jumun jumun);

}
