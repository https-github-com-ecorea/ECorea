package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.entity.*;

@Mapper
public interface AddressDao {
	
	/* 배송지 추가 */
	public void addAddress(Address address);
	
	/* 해당 아이디에 기존에 저장된 배송지가 있는지 확인 */
	public Integer checkAddressByMemberId(String memberId);
	
	/* 배송지 목록 출력 */
	public List<Address> addressList(String loginId);
	
	/* 선택한 배송지 정보 출력 */
	public Address chosenAddress(String memberId, Integer ano);
	
	/* 기본배송지 정보 출력 */
	public Address defaultAddress(String memberId);
	
	/* 배송지 삭제 */
	public Integer deleteAddress(String memberId, Integer ano);
	
}
