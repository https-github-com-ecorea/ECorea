package com.project.ecorea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.project.ecorea.dao.AddressDao;
import com.project.ecorea.service.AddressService;

@SpringBootTest
@WebAppConfiguration
public class AddressTest {
	
	@Autowired
	private AddressService service;
	
	@Autowired
	private AddressDao dao;
	
	// @Test
	public void addressList() {
		System.out.println(service.addressList("kpython2"));
	}
	
	// @Test
	public void chosenAddressTest() {
		System.out.println(service.chosenAddress("kpython2", 1));
	}
	
	@Test
	public void addAddress() {
		System.out.println(dao.checkAddressByMemberId("kpython2"));
	}
	
}
