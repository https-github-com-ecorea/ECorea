package com.project.ecorea;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ChProveDto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

@SpringBootTest
public class ChProveTest {
	@Autowired
	ChProveDao chproveDao;
	@Autowired
	ChProveService proveService;
	String imagePath = "/images/";
	
	
	//@Test
	public void intiTest() {
		assertNotNull(chproveDao);
	}
	
	//@Test
	public void listTest() {
		String memberId = "spring11";		
		String imagePath = "/images/";
		List<ChProveDto.ProveList> result = chproveDao.findByMemberId(memberId, imagePath);
		System.out.println("############# result : " + result);		
	}
	
	//@Test
	public void saveTest() {
		ChProve prove = new ChProve(null, 1, "제목", "내용", LocalDate.now(), "이미지", "zzzzuny");
		chproveDao.saveChProve(prove);
	}
	
	//@Test
	public void saveServiceTest() {
		String memberId = "zzzzuny";
		ChProveDto.InputProve inputprove = InputProve.builder().cno(1).cptitle("제목이라").cpcontent("내애욘").build();
		proveService.UploadChProve(inputprove, memberId);
	}
	
	@Test
	public void applyTest() {
		String memberId = "spring11";
		//List<ChProveDto.ChallengeApply> result = chproveDao.applyFindByMemberId(memberId,cno);
		//System.out.println("################ result : " + result);
	}
}
