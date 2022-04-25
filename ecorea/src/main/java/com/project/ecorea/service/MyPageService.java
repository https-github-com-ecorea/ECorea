package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.*;

@Service
@AllArgsConstructor
public class MyPageService {
	private JumunDao jumunDao;
	private UserDao memberDao;
	
	public MemberDto.Mypage readMyPage(String memberId) {
		Integer point = memberDao.memberFindById(memberId).getPoint();
		List<JumunDto.JumunStatus> statusList = jumunDao.countShippingStatus(memberId);
		return new MemberDto.Mypage(point, statusList);
	}
}
