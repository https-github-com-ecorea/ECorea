package com.project.ecorea;

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
public class ProductTest {
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
