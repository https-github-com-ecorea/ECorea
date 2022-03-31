package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

@SpringBootTest
public class CartTest {
	@Autowired
	CartDao cartDao;
	@Autowired
	CartService cartService;
	@Autowired
	ProductDao productDao;

	
	
	//@Test
	public void init() {
		assertNotNull(cartDao);
	}
	
	//@Test
	public void readCartServiceTest() {
		String memberId = "zzzzuny";
		List<CartDto.CartList> cartList = cartDao.findByMemberId(memberId);
		System.out.println("==============================");
		System.out.println("cartList : " + cartList);
		System.out.println("==============================");
	}
	
	//@Test
	public void plusCntTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer cartcnt = 1;
		Cart cart = new Cart(memberId, pno, cartcnt, 0, "tv");
		Integer result = cartDao.updateCntPlus(cart);
		System.out.println("===============================");
		System.out.println("result : " + result);
		System.out.println("===============================");
		assertEquals(result, 1);
	}
	
	//@Test
	public void minusCntTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer cartcnt = 1;
		Cart cart = new Cart(memberId, pno, cartcnt, 0, "tv");
		Integer result = cartDao.updateCntMinus(cart);
		System.out.println("===================================");
		System.out.println("result : " + result);
		System.out.println("===================================");
	}
	
	//@Test
	public void plusCntServiceTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer result = cartService.plusCnt(memberId, pno);
		assertEquals(result, 1);
	}
	
	//@Test
	public void minusCntServiceTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer result = cartService.minusCnt(memberId, pno);
		assertEquals(result, 1);
	}
	
	@Transactional
	//@Test
	public void deleteOneTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer result = cartDao.deleteOne(memberId, pno);
		assertEquals(result, 1);
	}
	
	@Transactional
	//@Test
	public void deleteAllTest() {
		String memberId = "zzzzuny";
		Integer result = cartDao.deleteAll(memberId);
		assertEquals(result, 4);
	}
	
	@Transactional
	//@Test
	public void deleteSelectedTest() {
		String memberId = "zzzzuny";
		List<Integer> pnos = new ArrayList<Integer>(Arrays.asList(1,2));
		Integer result = cartDao.deleteSelected(memberId, pnos);
		assertEquals(result, 2);		
	}
	
	@Transactional
	//@Test
	public void deleteOneServiceTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer result = cartService.deleteOne(memberId, pno);
		assertEquals(result, 1);
	}
	
	@Transactional
	//@Test
	public void deleteAllServiceTest() {
		String memberId = "zzzzuny";
		Integer result = cartService.deleteAll(memberId);
		assertEquals(result, 4);
	}
	
	@Transactional
	//@Test
	public void deleteSelectedServiceTest() {
		String memberId = "zzzzuny";
		List<Integer> pnos = new ArrayList<Integer>(Arrays.asList(1,2));
		CartDto.DeleteSelected dto = new CartDto.DeleteSelected(pnos);
		Integer result = cartService.deleteSelected(memberId, dto);

		System.out.println("====================================");
		System.out.println("dto : " + dto);
		System.out.println("====================================");
    
		assertEquals(result, 2);
	}
	
	//@Test
	public void saveTest() {
		Integer pno = 5;
		String memberId = "zzzzuny";
		Integer cartcnt = 1;
		Product product = productDao.findByPno(pno);
		System.out.println("#########################" + product);		
		Integer cartPrice = product.getPrice() * cartcnt;
		Cart cart = Cart.builder().memberId(memberId).cartpname(product.getPname()).pno(pno).cartcnt(1).cartprice(cartPrice).build();
		System.out.println("#########################" + cart);
		cartDao.saveOneProduct(cart);				
	}	
}
