package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.CartDto.*;
import com.project.ecorea.dto.JumunDto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

@SpringBootTest
public class JumunTest {
	@Autowired
	JumunDao jumunDao;
	@Autowired
	JumunService jumunService;
	
	//@Test
	public void init() {
		assertNotNull(jumunDao);
	}
	
	//@Test
	public void save() {
		Jumun jumun = new Jumun(null, 1, 2, ShippingStatus.PAY, 2000, 5, LocalDate.now(), "zzzzuny", 21, "문 앞");
		Integer result = jumunDao.saveJumun(jumun);
		assertEquals(result, 1);
	}
	
	//@Test
	public void saveServiceTest() {
		JumunInput input = new JumunInput(21, 5, "문 앞에 놔주세요");
		List<CartProduct> products = new ArrayList<>();
		CartProduct product1 = new CartProduct(1, "tv", 3, 1000, "imgchange");
		CartProduct product2 = new CartProduct(3, "스피커", 1, 10000, "imag3");
		products.add(product1);
		products.add(product2);
		JumunPreview dto = new JumunPreview(products, 10, 33000, "zzzzuny", "sdfa@asdfa.com");
		String memberId = "zzzzuny";
		
		jumunService.newJumun(input, dto, memberId);
	}
	
	@Test
	public void readList() {
		String memberId = "zzzzuny";
		String imagePath = "/images/";
		List<JumunDto.JumunList> jumunList = jumunDao.findByMemberId(memberId, imagePath);
		System.out.println("###################################");
		System.out.println("jumun list : " + jumunList);
		System.out.println("###################################");
	}
}
