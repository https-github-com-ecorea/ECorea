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
	private ProductDao productDao;
	private CartDao cartDao;
	private CartService cartService;
	
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
	
	// 관심상품 한개 장바구니에 담기
	public Boolean shoppingCartOne(Integer pno, String memberId) {
		// cart에 이미 담겨있는 상품인지 확인 
		// 담겨있으면 수량 1증가, 아니면 saveOneProduct		
		Integer cartcnt = 1;
		Cart existProduct = cartDao.findByMemberIdAndPno(memberId, pno);
		if(existProduct==null) {
			Product product = productDao.findByPno(pno);
			Integer cartPrice = product.getPrice()*cartcnt;
			Cart cart = Cart.builder().memberId(memberId).pno(pno).cartcnt(cartcnt)
					.cartpname(product.getPname()).cartprice(cartPrice).build();
			if(cart==null) {
				return false;
			} else {
				cartDao.saveOneProduct(cart);
				return true;
			}			
		} else {
			cartService.plusCnt(memberId, pno);
			return true;
		}		
	}
	
}
