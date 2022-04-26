package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.MemberDto.*;

import lombok.*;

@Service
@AllArgsConstructor
public class MyPageService {
	private JumunDao jumunDao;
	private UserDao memberDao;
	
	public MemberDto.Mypage readMyPage(String memberId) {
		Integer point = memberDao.memberFindById(memberId).getPoint();
		Integer payCnt = 0;
		Integer shippingCnt = 0;
		Integer completeCnt = 0;
		Integer cancelCnt = 0;
		List<JumunDto.JumunStatus> statusList = jumunDao.countShippingStatus(memberId);
		for(JumunDto.JumunStatus status: statusList) {
			if(status.getJstatus().equals("PAY")) {
				payCnt = status.getStatusCnt();
			System.out.println("######### paycnt #########");
			System.out.println("########## status : " + status.getJstatus());
			System.out.println("########## cnt : " + status.getStatusCnt());
			System.out.println("########## payCnt : " + payCnt);
			}
			else if(status.getJstatus().equals("SHIPPING")) 
				shippingCnt = status.getStatusCnt();
			else if(status.getJstatus().equals("COMPLETE"))
				completeCnt = status.getStatusCnt();
			else if(status.getJstatus().equals("RETURN") || status.getJstatus().equals("CANCEL")) {
				cancelCnt += status.getStatusCnt();
			}			
		}
		return new Mypage(point, payCnt, shippingCnt, completeCnt, cancelCnt);
	}
}
