package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

@SpringBootTest
public class BookmarkTest {
	@Autowired
	BookmarkDao bookmarkDao;
	
	//@Test
	public void initTest() {
		assertNotNull(bookmarkDao);
	}
	
	//@Test
	public void listTest() {
		List<BookmarkDto.BookmarkList> result = bookmarkDao.findByMemberId("zzzzuny");
		System.out.println("############### result : " + result);
	}
	
	@Transactional
	//@Test
	public void deleteOneTest() {
		String memberId = "zzzzuny";
		Integer pno = 1;
		Integer result = bookmarkDao.deleteOne(memberId, pno);
		assertEquals(result, 1);
	}
	
	@Transactional
	//@Test
	public void deleteAllTest() {
		String memberId = "zzzzuny";
		Integer result = bookmarkDao.deleteAll(memberId);
		assertEquals(result, 2);
	}
	
	@Transactional
	@Test
	public void deleteSelectedTest() {
		String memberId = "zzzzuny";
		List<Integer> pnos = new ArrayList<>(Arrays.asList(1,3));
		Integer result = bookmarkDao.deleteSelected(memberId, pnos);
		assertEquals(result, 2);
		
	}
}
