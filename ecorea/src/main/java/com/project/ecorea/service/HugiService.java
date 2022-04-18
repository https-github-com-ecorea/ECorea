package com.project.ecorea.service;

import java.io.*;
import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.HugiDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class HugiService {
	@Value("${upload.image.folder}")
	private String imageFolder;
	@Value("${upload.image.path}") 
	private String imagePath;
	@Value("${default.image.name}")
	private String defaultImage;

	private final HugiDao dao;
	
	/* 후기 목록 출력 */
	public List<HugiDto.HugiList> hugiList(Integer pno) {
		List<HugiDto.HugiList> hugis = dao.findByPno(pno);
		return hugis;
	}
	
	/* 일반 회원 후기 목록 출력 */
	public List<HugiDto.HugiList> memberHugiList(String loginId) {
		List<HugiDto.HugiList> memberHugis = dao.findByhwriter(loginId);
		for(HugiDto.HugiList hugi : memberHugis) {
			hugi.setHimg(imagePath + hugi.getHimg());
		}
		
		return memberHugis;
	}

	/* 일반 회원 후기 삭제 */
	public void reviewDelete(Integer hno) {
		dao.deleteByHno(hno);
	}

	/* 일반 회원 상품 등록 */
	public void reviewUpload(String loginId, HugiDto.HugiUpload upload) {
		Hugi hugi = upload.toEntity();
		MultipartFile image = upload.getHimg();
		hugi.setHimg(defaultImage);
		// MultipartFile이 null이 아니고 비어있지 않고 이미지 파일(image/jpeg, image/png.....)이라면
		if(image!=null && image.isEmpty()==false && image.getContentType().toLowerCase().startsWith("image/")) {
			String imagename = UUID.randomUUID() + "-" + image.getOriginalFilename();
			File file = new File(imageFolder, imagename);
			try {
				image.transferTo(file);
				hugi.setHimg(imagename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		hugi.setHwriter(loginId).setHregday(LocalDate.now());
				
		dao.reviewSave(hugi);
	}

	public void reviewUpdate(Integer hno, HugiUpdate update) {
		Hugi hugi = update.toEntity();
		
		MultipartFile image = update.getHimg();
		if(image!=null && image.isEmpty()==false && image.getContentType().toLowerCase().startsWith("image/")) {
			String imagename = UUID.randomUUID() + "-" + image.getOriginalFilename();
			File file = new File(imageFolder, imagename);
			try {
				image.transferTo(file);
				hugi.setHimg(imagename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			hugi.setHimg(defaultImage);			
		}
				
		dao.updateByReview(hugi);
	}

	public Hugi readReviewUpdate(String loginId, Integer hno) {
		Hugi hugi = dao.findByHno(hno);
		return hugi;
	}
}