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
	public Boolean addAddress(String /*loginId*/username, Address address) {
		username = "dnazer0";
		address.setMemberId(/*loginId*/username);
		dao.addAddress(address);
		if (dao.checkAddressByMemberId(/*loginId*/username) <= 0) {
			address.setAstandard(1);
			return false;
		}
		return true;
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
