package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@Service
@AllArgsConstructor
public class BookmarkService {
	private BookmarkDao bookmarkDao;
	
	// 관심상품 리스트 출력
	public List<BookmarkDto.BookmarkList> readBookmark(String memberId) {
		List<BookmarkDto.BookmarkList> list = bookmarkDao.findByMemberId(memberId);
		return list;
	}
	
	// 관심상품 한개 삭제
	public Boolean deleteOne(String memberId, Integer pno) {
		Integer deleteOneResult = bookmarkDao.deleteOne(memberId, pno);
		if(deleteOneResult<=0) 
			return false;
		return true;
	}
	
	
	// 관심상품 전체 삭제
	public Boolean deleteAll(String memberId) {
		Integer deleteAllResult = bookmarkDao.deleteAll(memberId);
		if(deleteAllResult<=0)
			return false;
		return true;
	}
	
	// 관심상품 선택 삭제
	public Boolean deleteSelected(String memberId, BookmarkDto.PnoSelected dto) {
		Integer deleteSelectedResult = bookmarkDao.deleteSelected(memberId, dto.getPnos());
		if(deleteSelectedResult<=0)
			return false;
		return true;
	}
	
}
