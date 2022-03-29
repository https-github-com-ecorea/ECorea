package com.project.ecorea;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
public class ProductTest {
	
	@Autowired
	private ProductService service;
	
	// @Test
	public void productReadTest() {
		System.out.println(service.productRead(10));
	}
	
	// @Test
	public void checkStockTest() {
		System.out.println(service.checkStock(1, 5));
	}
	
	@Test
	public void productListTest() {
		System.out.println(service.productList(1, "22"));
	}
	
}
