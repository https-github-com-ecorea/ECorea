package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class OrderMvcController {
	private OrderService orderService;
	
	@PostMapping("/order/pay")
	public void cartToOrder() {
		
	}

}
