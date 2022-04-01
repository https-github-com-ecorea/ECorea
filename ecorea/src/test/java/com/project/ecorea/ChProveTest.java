package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

@SpringBootTest
public class ChProveTest {
	@Autowired
	ChProveDao chproveDao;
	
	//@Test
	public void intiTest() {
		assertNotNull(chproveDao);
	}
	
	@Test
	public void listTest() {
		String memberId = "zzzzuny";
		List<ChProveDto.ProveList> result = chproveDao.findByMemberId(memberId);
		System.out.println("############# result : " + result);
		
	}
}
