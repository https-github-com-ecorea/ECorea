package com.project.ecorea;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.format.annotation.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
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

	@Autowired
	ProductDao productDao;
	@Autowired
	ProductService productService;
	
	//@Test
	public void init() {
		assertNotNull(productDao);
	}
	
	//@Test
	public void saveTest() {		
		Product product = Product.builder().pno(100).pcategory("11").price(1325).pname("티비").pstock(30).pcontent("싸요싸요")
		.pthumbnail("iu").pordercnt(0).pregday(LocalDate.now()).corpId("lg").build();
		productDao.save(product);
	}
	
	//@Test
	public void findByCorpIdTest() {
		List<ProductDto.corpProductList> list = productDao.findByCorpId("samsung");
		System.out.println("==============================");
		System.out.println("findByCorpId : " + list);
		System.out.println("==============================");
	}
	
	@Test
	public void regProductListTest() {
		List<ProductDto.corpProductList> list = productService.regProductsList("lg");
		System.out.println("==============================");
		System.out.println("regProductList : " + list);
		System.out.println("==============================");
	}
  
}
