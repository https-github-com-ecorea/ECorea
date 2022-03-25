package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

@SpringBootTest
public class CartTest {
	@Autowired
	CartDao cartDao;

	
	
	//@Test
	public void init() {
		assertNotNull(cartDao);
	}
	
	@Test
	public void readCartServiceTest() {
		String memberId = "zzzzuny";
		List<CartDto.CartList> cartList = cartDao.findByMemberId(memberId);
		System.out.println("==============================");
		System.out.println("cartList : " + cartList);
		System.out.println("==============================");
	}
}
