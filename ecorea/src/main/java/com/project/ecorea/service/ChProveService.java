package com.project.ecorea.service;

import java.time.*;
import java.util.*;

import org.springframework.format.annotation.*;
import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

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
	
	// 챌린지 인증 등록
	public void UploadChProve(ChProveDto.InputProve dto, String memberId) {
		ChProve chprove = dto.toEntity();
		chprove.setCpregday(LocalDate.now()).setMemberId(memberId);
		proveDao.saveChProve(chprove);		
	}
}
