package com.project.ecorea.service;

import java.io.*;
import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class ChProveService {
	private final ChProveDao proveDao;
	
	@Value("${upload.image.path}")
	private String imagePath;	
	@Value("${default.image.name}")
	private String defaultImage;
	@Value("${upload.image.folder}")
	private String imageFolder; 
	
	// 나의 챌린지 목록 출력 - 인증 O
	public List<ChProveDto.ProveList> readProve(String memberId) {
		List<ChProveDto.ProveList> proveList = proveDao.findByMemberId(memberId, imagePath);		
		return proveList;
	}
	
	// 나의 챌린지 목록 출력 - 인증 X
	public List<ChProveDto.ChallengeApply> readChApply(String memberId) {
		List<Integer> checkList = proveDao.applyCheckList(memberId);
		List<ChProveDto.ChallengeApply> applyList = new ArrayList<>();
		for(Integer cno: checkList) {
			Integer result = proveDao.countChApply(memberId, cno);
			if(result == 0) {
				applyList.add(proveDao.applyFindByMemberId(memberId, cno)); 
			}
		}
		return applyList;
	}
	
	// 챌린지 인증 등록
	public void UploadChProve(ChProveDto.InputProve dto, String memberId) {
		ChProve chprove = dto.toEntity();
		
		MultipartFile image = dto.getCpimg();
		chprove.setCpimg(defaultImage);
		if(image!=null && image.isEmpty()==false && image.getContentType().toLowerCase().startsWith("image/")) {
			String cpImagename = UUID.randomUUID() + "-" + image.getOriginalFilename();
			File file = new File(imageFolder,cpImagename);
			try {
				image.transferTo(file);
				chprove.setCpimg(cpImagename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}			
		}		
		chprove.setCpregday(LocalDate.now()).setMemberId(memberId);
		proveDao.saveChProve(chprove);		
	}
	
	// 챌린지 인증 삭제
	public Integer deleteChProve(String memberId, Integer cpno) {
		Integer result = proveDao.deleteByMemberIdAndCpno(memberId, cpno);
		return result;
	}
	
	// 챌린지 신청 취소
	public Integer cancelJoin(String memberId, Integer cno) {
		Integer result = proveDao.deleteByMemberIdAndCno(memberId, cno);
		return result;
	}
	
	/* 챌린지 상세 : 인증 목록 (페이징) */
	public PagingChProveDto challengeDetailProveList(Criteria cri) {
		PagingChProveDto dto = new PagingChProveDto();
		List<ChProveDto.ChallengeDetailProveList> entity = proveDao.findByProveAll(cri);
		for (ChProveDto.ChallengeDetailProveList prove : entity) {
			prove.setCpimg(imagePath + prove.getCpimg());
		}
		dto.setList(entity);
		dto.setPageInfo(new PageMakerDto(cri, proveDao.getChallengeDetailTotal(cri.getCno())));
		return dto;
	}
	
}
