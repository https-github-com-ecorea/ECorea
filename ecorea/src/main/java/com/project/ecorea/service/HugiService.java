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
import com.project.ecorea.dto.QnaDto.QuestionDto;
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
	
	/* 상품 상세 : 후기 목록 (페이징) */
	public PagingHugiDto productDetailHugiList(Criteria cri) {
		PagingHugiDto dto = new PagingHugiDto();
		List<HugiDto.HugiList> entity = dao.productDetailHugiFindbyPno(cri);
		for (HugiDto.HugiList hugi : entity) {
			hugi.setHimg(imagePath + hugi.getHimg());
		}
		dto.setList(entity);
		dto.setPageInfo(new PageMakerDto(cri, dao.getProductDetailTotal(cri.getPno())));
		return dto;
	}
	
	/* 일반 회원 후기 목록 출력 */
	public List<HugiDto.HugiList> memberHugiList(String loginId, Criteria cri) {
		List<HugiDto.HugiList> memberHugiList = new ArrayList<>();
		// 로그인한 회원의 아이디로 작성한 후기글들을 db에서 읽어온다
		List<HugiDto.HugiList> hugiPaging = dao.findByhwriter(cri);
		// 가지고 온 후기 글들의 이미지에 imagePath 주소값을 더해준다
		for(HugiDto.HugiList hugi : hugiPaging) {
			if(hugi.getHwriter().equals(loginId)) {
				hugi.setHimg(imagePath + hugi.getHimg());
				memberHugiList.add(hugi);
			}
		}
		
		return memberHugiList;
	}

	/* 일반 회원 후기 삭제 */
	public void reviewDelete(Integer hno) {
		dao.deleteByHno(hno);
	}

	/* 일반 회원 후기 등록 */
	public void reviewUpload(String loginId, HugiDto.HugiUpload upload) {
		// 입력받은 데이터를 Entity 형태로 변환
		Hugi hugi = upload.toEntity();
		MultipartFile image = upload.getHimg();
		// 이미지가 없을 경우 사용하게 될 기본 이미지
		hugi.setHimg(defaultImage);
		hugi.setJno(upload.getJno());
		// MultipartFile이 null이 아니고 비어있지 않고 이미지 파일(image/jpeg, image/png.....)이라면
		if(image!=null && image.isEmpty()==false && image.getContentType().toLowerCase().startsWith("image/")) {
			// 데이터베이스에 저장될 이미지 이름 생성
			String imagename = UUID.randomUUID() + "-" + image.getOriginalFilename();
			// file 변수에 이미지를 저장할 주소 , 이미지 이름을 가진 File 객체를 생성
			File file = new File(imageFolder, imagename);
			try {
				// file 변수에 담긴 File 객체를 저장한다
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
		// 입력받은 데이터를 Entity 형태로 변환
		Hugi hugi = update.toEntity();
		MultipartFile image = update.getHimg();
		// MultipartFile이 null이 아니고 비어있지 않고 이미지 파일(image/jpeg, image/png......)이라면
		if(image!=null && image.isEmpty()==false && image.getContentType().toLowerCase().startsWith("image/")) {
			// 데이터베이스에 저장될 이미지 이름 생성
			String imagename = UUID.randomUUID() + "-" + image.getOriginalFilename();
			// file 변수에 이미지를 저장할 주소 , 이미지 이름을 가진 File 객체를 생성
			File file = new File(imageFolder, imagename);
			try {
				// file 변수에 담긴 File 객체를 저장한다
				image.transferTo(file);
				hugi.setHimg(imagename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			// 이미지가 null 이라면 기본이미지를 넣는다
			hugi.setHimg(defaultImage);			
		}
				
		dao.updateByReview(hugi);
	}

	public Hugi readReviewUpdate(String loginId, Integer hno) {
		Hugi hugi = dao.findByHno(hno);
		hugi.setHimg(imagePath + hugi.getHimg());
		return hugi;
	}

	public int getHugiListTotal(String loginId) {
		return dao.findHugiList(loginId);
	}
}