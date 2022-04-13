package com.project.ecorea;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.format.annotation.*;

import org.springframework.transaction.annotation.*;


import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ProductDto.productList;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
@Slf4j
public class ProductTest {
	
	@Autowired
	private ProductService service;
	
	// @Test
	public void productReadTest() {
		System.out.println(service.productRead(10));
	}
	
	// @Test
	public void checkStockTest() {
		System.out.println(service.checkStock(10, 5));
	}
	
	 /* 게시판 목록(페이징 적용)테스트 */
	 // @Test
	 public void listPagingDaoTest() {
	     Criteria cri = new Criteria();
	     cri.setNowPage(2);
	     List<productList> list = productDao.productPagingList(cri);
	     list.forEach(element -> log.info("" + element));
	     /* log.info 쓰려면 테스트 클래스 상단에 @Slf4j 선언해 줘야 함 */
	 }
	 
	 @Test
	 public void listPagingServiceTest() {
		 Criteria cri = new Criteria();
		 List<productList> list = service.productPagingList(cri);
		 list.forEach(element -> log.info("" + element));
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

		Product product = Product.builder().pno(100).catecode("11").price(1325).pname("티비").pstock(30).pcontent("싸요싸요")
		.pthumbnail("iu").pordercnt(0).pregday(LocalDate.now()).corpId("lg").build();
		productDao.save(product);
	}
	
	//@Test
	public void findByCorpIdTest() {
		List<ProductDto.CorpProductList> list = productDao.findByCorpId("samsung", "/images/");

		System.out.println("==============================");
		System.out.println("findByCorpId : " + list);
		System.out.println("==============================");
	}
	
	//@Test
	public void regProductListTest() {
		List<ProductDto.CorpProductList> list = productService.regProductsList("lg");

		System.out.println("==============================");
		System.out.println("regProductList : " + list);
		System.out.println("==============================");
	}

	//@Test
	public void readProductDetailForUpdateTest() {
		ProductDto.ProductDetailForUpdate product = productDao.findByCorpIdAndPno("samsung", 1);
		System.out.println("product : " + product);
		assertNotNull(product);
	}
	
	//@Test
	public void productDetailForUpdateServiceTest() {
		String corpId = "samsung";
		Integer pno = 1;
		ProductDto.ProductDetailForUpdate dto = productService.readProductDetailForUpdate(corpId, pno);
		System.out.println("=============================");
		System.out.println("dto : " + dto);
		System.out.println("=============================");
	}
	
	@Transactional
	//@Test
	public void updateTest() {
		String corpId = "samsung";
		Integer pno = 1;
		ProductDto.UpdateProduct updateProduct = new ProductDto.UpdateProduct(pno, "상품명변경", 1000, 100, "썸네일변경", "제품내용내용", corpId);
		Integer result = productDao.updateProduct(updateProduct);
		System.out.println("=====================================");
		System.out.println("result : " + result);
		System.out.println("=====================================");				
	}
	
	@Transactional
	//@Test
	public void updateServiceTest() {
		String corpId = "samsung";
		Integer pno = 1;
		ProductDto.UpdateProduct productDto = new ProductDto.UpdateProduct(pno, "상품명변경", 1000, 10, "썸네일변경", "내용", corpId); 
		Boolean result = productService.updateProduct(productDto);
		assertEquals(result, true);
	}
	
	@Transactional
	//@Test
	public void deleteTest() {
		String corpId = "samsung";
		Integer pno = 1;
		Integer result = productDao.deleteProduct(corpId, pno);
		System.out.println("result : " + result);
		assertEquals(result, 1);
	}
}
