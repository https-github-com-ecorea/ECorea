package com.project.ecorea.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;

@Mapper
public interface UserDao {

	public void memberSave(Member member);
	
	public void corpSave(Corp corp);
	
	public Member findByCheckcode(String checkcode);
	
	public Integer memberInfoUpdate(Member member);
	
	public Member memberFindByEmailAndName(String email, String name);
	
	public Corp corpFindByEmailAndName(String email, String name);

	public Member memberFindByIdAndEmail(String id, String email);
	
	public Corp corpFindByIdAndEmail(String id, String email);

	public Integer corpInfoUpdate(Corp corp);

	public Member memberFindById(String id);

	public Corp corpFindById(String id);

	public void memberDeleteById(String id);

	public void corpDeleteById(String loginId);
	
	public Integer memberUsePointUpdate(String id, Integer point);
	
	public Integer memberGetPointUpdate(String id, Integer point);
}
