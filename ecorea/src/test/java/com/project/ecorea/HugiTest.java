package com.project.ecorea;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.web.*;

import com.project.ecorea.service.*;

@SpringBootTest
@WebAppConfiguration
public class HugiTest {
	
	@Autowired
	private HugiService service;
	
	@Test
	public void hugiListTest() {
		System.out.println(service.hugiList(10));
	}

}
