package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Service
@AllArgsConstructor
public class AddressService {

	private AddressDao dao;
	
	/* 배송지 추가 */
	public Boolean addAddress(String loginId, Address address) {
		address.setMemberId(loginId);
		if (dao.checkAddressByMemberId(loginId) == false)
			address.setAstandard(true);
		return dao.addAddress(address) == 1;
	}
	
	/* 배송지 목록 */
	public List<Address> addressList(String loginId) {
		return dao.addressList(loginId);
	}
	
	/* 선택한 배송지 정보 */
	public Address chosenAddress(String memberId, Integer ano) {
		return dao.chosenAddress(memberId, ano);
	}
	
}
