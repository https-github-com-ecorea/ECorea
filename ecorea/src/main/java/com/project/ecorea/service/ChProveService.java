package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.*;

@AllArgsConstructor
@Service
public class ChProveService {
	private ChProveDao proveDao;
	
	// 나의 챌린지 목록 출력
	public List<ChProveDto.ProveList> readProve(String memberId) {
		List<ChProveDto.ProveList> proveList = proveDao.findByMemberId(memberId);
		
		return proveList;
	}
}
