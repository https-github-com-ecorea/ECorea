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
	
	
	@PostMapping("/order/jumunsheets")
	public ModelAndView cartToOrder(JumunDto params) {
		String memberId = "zzzzuny";
		JumunDto.JumunSheet dto = jumunService.jumunList(params.getList(), memberId);
		return new ModelAndView("order/pay").addObject("", dto);
	}
	
}