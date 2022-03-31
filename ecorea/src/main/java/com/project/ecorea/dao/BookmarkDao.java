package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;

@Mapper
public interface BookmarkDao {
	
	// 관심상품 리스트 출력
	public List<BookmarkDto.BookmarkList> findByMemberId(String memberId);
	
	// 관심상품 한 개 삭제
	public Integer deleteOne(String memberId, Integer pno);
	
	// 관심상품 전체 삭제
	public Integer deleteAll(String memberId);
	
	// 관심상품 선택 삭제
	public Integer deleteSelected(String memberId, List<Integer> pnos);
	
}
