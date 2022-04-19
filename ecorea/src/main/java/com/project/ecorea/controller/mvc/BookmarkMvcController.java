package com.project.ecorea.controller.mvc;

import java.security.*;
import java.util.*;

import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
@Secured("ROLE_MEMBER")
public class BookmarkMvcController {
	private BookmarkService bookmarkService;
	private ProductService productService;
	
	// 관심상품 목록 출력
	@GetMapping("/mypage/member/bookmarkList")
	public ModelAndView readBookmark(Principal principal) {
		List<BookmarkDto.BookmarkList> bookmarkList = bookmarkService.readBookmark(principal.getName());
		return new ModelAndView("mypage/member/bookmarkList").addObject("bookmarkList", bookmarkList);		
	}
	
	// 관심상품 한개 삭제
	@PostMapping("/mypage/member/bookmarkList/deleteOne")
	public String deleteOne(Integer pno, Principal principal) {
		bookmarkService.deleteOne(principal.getName(), pno);
		return "redirect:/mypage/member/bookmarkList";
	}

	// 관심상품 전체 삭제 
	@PostMapping("/mypage/member/bookmarkList/deleteAll")
	public String deleteAll(Principal principal) {
		bookmarkService.deleteAll(principal.getName());
		return "redirect:/mypage/member/bookmarkList";
	}
	
	// 관심상품 선택 삭제
	@PostMapping("/mypage/member/bookmarkList/deleteSelected")
	public String deleteSelected(BookmarkDto.PnoSelected dto, Principal principal) {
		bookmarkService.deleteSelected(principal.getName(), dto);
		return "redirect:/mypage/member/bookmarkList";
	}
	
	// 관심상품 한 개 장바구니에 담기
	@PostMapping("/mypage/member/bookmarkList/shoppingOne")
	public String shoppingCartOne(Integer pno, Principal principal) {		
		productService.shoppingCartOne(pno, principal.getName());	
		return "redirect:/order/cart";
	}
	
	// 상품상세 -> 관심상품 등록
	@PostMapping("/bookmark/add")
	public String addBookmark(Integer pno, Principal principal) {
		bookmarkService.addBookmark(pno, principal.getName());
		return "redirect:/mypage/member/bookmarkList";
	}
}
