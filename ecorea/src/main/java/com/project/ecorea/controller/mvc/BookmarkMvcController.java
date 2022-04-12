package com.project.ecorea.controller.mvc;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class BookmarkMvcController {
	private BookmarkService bookmarkService;
	private ProductService productService;
	
	// 관심상품 목록 출력
	@GetMapping("/mypage/member/bookmarkList")
	public ModelAndView readBookmark() {
		String memberId = "zzzzuny";
		List<BookmarkDto.BookmarkList> bookmarkList = bookmarkService.readBookmark(memberId);
		return new ModelAndView("mypage/member/bookmarkList").addObject("bookmarkList", bookmarkList);		
	}
	
	// 관심상품 한개 삭제
	@PostMapping("/mypage/member/bookmarkList/deleteOne")
	public String deleteOne(Integer pno) {
		String memberId = "zzzzuny";
		bookmarkService.deleteOne(memberId, pno);
		return "redirect:/mypage/member/bookmarkList";
	}

	// 관심상품 전체 삭제 
	@PostMapping("/mypage/member/bookmarkList/deleteAll")
	public String deleteAll() {
		String memberId = "zzzzuny";
		bookmarkService.deleteAll(memberId);
		return "redirect:/mypage/member/bookmarkList";
	}
	
	// 관심상품 선택 삭제
	@PostMapping("/mypage/member/bookmarkList/deleteSelected")
	public String deleteSelected(BookmarkDto.PnoSelected dto) {
		String memberId = "zzzzuny";
		bookmarkService.deleteSelected(memberId, dto);
		return "redirect:/mypage/member/bookmarkList";
	}
	
	// 관심상품 한 개 장바구니에 담기
	@PostMapping("/mypage/member/bookmarkList/shoppingOne")
	public String shoppingCartOne(Integer pno) {		
		String memberId = "zzzzuny";
		productService.shoppingCartOne(pno, memberId);	
		return "redirect:/order/cart";
	}	
}
