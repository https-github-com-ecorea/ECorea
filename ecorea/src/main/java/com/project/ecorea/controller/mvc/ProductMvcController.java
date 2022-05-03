package com.project.ecorea.controller.mvc;


import org.springframework.web.bind.annotation.*;
import com.project.ecorea.service.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.security.Principal;

import javax.validation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.project.ecorea.dao.ProductDao;
import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;


@Controller
@AllArgsConstructor
@Slf4j
public class ProductMvcController {
	
	@Autowired
	private ProductService productService;
	
	/* 상품 목록 화면 
	@GetMapping("/")
	public ModelAndView main() {
		return new ModelAndView("/index").addObject("list", productService.productList());
	}
	*/
	
	/* 상품 목록 화면 (페이징 적용) */
	@GetMapping("product/productList")
	public void productPagingList(Model model, Criteria cri, HttpServletRequest request) {
		log.info("productPagingList");
		model.addAttribute("list", productService.productPagingList(cri));
		int total = productService.getCategoryTotal(cri.getCatecode());
		PageMakerDto pageMaker = new PageMakerDto(cri, total);
		model.addAttribute("pageMaker", pageMaker);
		if (request.isUserInRole("ROLE_MEMBER")) {
			model.addAttribute("role", "ROLE_MEMBER");
		} else if (request.isUserInRole("ROLE_CORP")) {
			model.addAttribute("role", "ROLE_CORP");
		}
	}
	
	/* 일반 회원 : 상품 상세 페이지 화면 */
	@GetMapping("/product/member/productDetail")
	public ModelAndView productRead(Integer pno, HttpSession session, HttpServletRequest request, Principal principal) {
		session.setAttribute("pno", pno);
		if (request.isUserInRole("ROLE_MEMBER"))
			return new ModelAndView("product/member/productDetail").addObject("product", productService.productRead(pno)).addObject("role", "ROLE_MEMBER");
		else if (request.isUserInRole("ROLE_CORP"))
			return new ModelAndView("product/corp/productDetail").addObject("product", productService.productRead(pno));
		return new ModelAndView("product/member/productDetail").addObject("product", productService.productRead(pno));
	}
	
//	// 기업회원 상품 상세페이지
//	@GetMapping("/product/corp/productDetail/{pno}")
//	public ModelAndView corpProductDetail(@PathVariable Integer pno) {
//		return new ModelAndView("product/corp/productDetail").addObject("product", productService.productRead(pno));
//	}

	 /* 기업 회원 상품 상세 페이지 */
	 @GetMapping("/product/corp/productDetail")
	 public ModelAndView corpProductDetail(Integer pno, HttpSession session) {
	 	session.setAttribute("pno", pno);
	 	return new ModelAndView("product/corp/productDetail").addObject("product", productService.productRead(pno)).addObject("role", "ROLE_CORP");
	}
	
	/* 문의 작성 버튼 */
	@Secured("ROLE_MEMBER")
	@GetMapping("/product/member/qnaUpload")
	public void qnaUpload() {
	}
	

	// 상품등록 페이지
	@Secured("ROLE_CORP")
	@GetMapping("/product/productUpload")
	public void uploadProduct(Model model) {
		model.addAttribute("role", "ROLE_CORP");
	}
	
	// 상품등록
	@Secured("ROLE_CORP")
	@PostMapping("/product/productUpload")
	public String uploadProduct(ProductDto.Upload uploadDto) {
		productService.uploadProduct(uploadDto);
		return "redirect:/mypage/corp/productList";
	}

	// 등록된 상품 리스트 보기
	@Secured("ROLE_CORP")
	@GetMapping("/mypage/corp/productList") 
	public ModelAndView regProductList(Principal principal) {
		return new ModelAndView("mypage/corp/productList").addObject("regProducts", productService.regProductsList(principal.getName())).addObject("role", "ROLE_CORP");
	}

	// 상품 수정페이지에서 등록된 상품 상세정보 출력
	@Secured("ROLE_CORP")
	@GetMapping("/product/corp/productUpdate")
	public ModelAndView readCorpProductDetail(Principal principal, Integer pno) {
		return new ModelAndView("product/corp/productUpdate")
				.addObject("product", productService.readProductDetailForUpdate(principal.getName(), pno));
	}
	
	// 상품 수정
	@Secured("ROLE_CORP")
	@PostMapping("/product/corp/productUpdate")
	public String updateProduct(@Valid ProductDto.UpdateProduct updateDto, RedirectAttributes ra) {
		if((updateDto.getPname()==null || updateDto.getPname().equals("")) && 
				(updateDto.getPrice()==null || updateDto.getPrice().equals("")) && 
				(updateDto.getPstock()==null || updateDto.getPstock().equals("")) && 
				(updateDto.getPthumbnail()==null || updateDto.getPthumbnail().equals("")) && 
				(updateDto.getPcontent()==null || updateDto.getPcontent().equals(""))) {
			ra.addFlashAttribute("serverMsg","값을 정확히 입력해주세요.");
			return "redirect:/mypage/corp/productList";
		}
		else {
			Boolean result = productService.updateProduct(updateDto);
			if(result==false) {
				ra.addFlashAttribute("serverMsg", "변경사항이 없습니다.");
				return "redirect:/mypage/corp/productList";
			}
			ra.addFlashAttribute("serverMsg","수정 성공");
			return "redirect:/mypage/corp/productList";
		}				
	}
	
	// 등록된 상품 삭제
	@Secured("ROLE_CORP")
	@PostMapping("/product/corp/productDelete")
	public String deleteProduct(Principal principal, Integer pno) {
		productService.deleteProduct(principal.getName(), pno);
		return "redirect:/mypage/corp/productList";
	}	
}


