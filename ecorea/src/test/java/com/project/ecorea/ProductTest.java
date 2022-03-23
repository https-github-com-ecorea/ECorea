package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.format.annotation.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.entity.*;

@SpringBootTest
public class ProductTest {
	@Autowired
	ProductDao productDao;
	
	//@Test
	public void init() {
		assertNotNull(productDao);
	}
	
	@Test
	public void saveTest() {		
		Product product = Product.builder().pno(100).pcategory("11").price(1325).pname("티비").pstock(30).pcontent("싸요싸요")
		.pthumbnail("iu").pordercnt(0).pregday(LocalDate.now()).corpId("lg").build();
		productDao.save(product);
	}
}
