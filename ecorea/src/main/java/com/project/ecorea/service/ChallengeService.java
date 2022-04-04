package com.project.ecorea.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ChallengeService {

	/* Property 읽어 오기 (경로 및 파일) */
	@Value("${product.image.path}")
	private String imagePath;
	@Value("${product.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
	private final ChallengeDao dao;
	
	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(ChallengeDto.challengeUpload challenge) {
		Challenge challengeDto = challenge.toEntity();
		MultipartFile cthumbnail = challenge.getCthumbnail();
		challengeDto.setCthumbnail(defaultImage);
		if (cthumbnail != null && cthumbnail.isEmpty() == false && cthumbnail.getContentType().toLowerCase().startsWith("image/")) {
			String imgname = UUID.randomUUID() + "-" + cthumbnail.getOriginalFilename();
			File file = new File(imageFolder, imgname);
			try {
				cthumbnail.transferTo(file);
				challengeDto.setCthumbnail(imgname);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		dao.challengeUpload(challengeDto);
	}

	/* 기업 회원 : 챌린지 수정 */
	public void challengeUpdate(Challenge challenge) {
		
		
	}

}
