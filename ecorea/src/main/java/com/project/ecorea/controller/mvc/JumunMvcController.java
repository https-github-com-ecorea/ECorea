package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;


@Controller
@AllArgsConstructor
public class JumunMvcController {
	private JumunService jumunService;
	
	/* 상품 상세 -> 바로 구매 */
	@PostMapping("/order/preview/one")
	public ModelAndView productToOrder(Integer pno, Integer count, String memberId) {
		JumunDto.JumunPreview dto = jumunService.jumunOne(pno, count, "ngoley6");
		return new ModelAndView("order/pay").addObject("preview", dto);
	}

	@PostMapping("/order/preview/multiple")
	public ModelAndView cartToOrder(JumunDto.ParamsList list) {
		String memberId = "zzzzuny";
		JumunDto.JumunPreview dto = jumunService.jumunList(list.getParamsList(), memberId);
		return new ModelAndView("order/pay").addObject("preview", dto);
	}
		
}