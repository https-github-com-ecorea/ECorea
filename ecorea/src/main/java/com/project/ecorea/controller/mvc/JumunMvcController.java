package com.project.ecorea.controller.mvc;

import java.security.*;
import java.util.*;

import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.web.servlet.support.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

import javax.servlet.http.*;

import lombok.*;


@Controller
@AllArgsConstructor
@Secured("ROLE_MEMBER")
public class JumunMvcController {
	private JumunService jumunService;
	
	/* 상품 상세 -> 바로 구매 */
	@PostMapping("/order/preview/one")
	public ModelAndView productToOrder(Integer pno, Integer count, Principal principal, HttpSession session) {
		JumunDto.JumunPreview dto = jumunService.jumunOne(pno, count, principal.getName());
		session.setAttribute("dto", dto);
		return new ModelAndView("order/pay").addObject("preview", dto);
	}

	// 장바구니 -> 구매 (주문 상품 여러개)
	@PostMapping("/order/preview/multiple")
	public ModelAndView cartToOrder(JumunDto.ParamsList list, Principal principal, HttpSession session) {
		JumunDto.JumunPreview dto = jumunService.jumunPreview(list.getList(), principal.getName());
		session.setAttribute("dto", dto);
		return new ModelAndView("order/pay").addObject("preview", dto);
	}
	
	//  /order/pay 에서 주문 버튼 -> 주문 완료 처리
	@PostMapping("/order/new")
	public String order(JumunDto.JumunInput input, Principal principal, HttpSession session, RedirectAttributes ra) {
		JumunDto.JumunPreview dto = (JumunDto.JumunPreview)session.getAttribute("dto");
		jumunService.newJumun(input, dto, principal.getName());
		ra.addFlashAttribute("isNew", true);
		return "redirect:/order/complete";
	}
	
	// 주문 성공 메세지 출력
	@GetMapping("/order/complete")
	public String orderComplete(HttpServletRequest request) {		
		// RedirectAttribute가 존재하는지 확인
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap==null)
			return "redirect:/";
		return "order/complete";
	}
	
	// 주문 목록 보기
	@GetMapping("/order/orderList")
	public ModelAndView orderList(Principal principal) {
		List<JumunDto.JumunList> jumunList = jumunService.readJumunList(principal.getName());
		return new ModelAndView("/order/orderList").addObject("jumunList", jumunList).addObject("role", "ROLE_MEMBER");
	}
		
}