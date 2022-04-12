package com.project.ecorea.service;

import java.time.*;
import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.CartDto.*;
import com.project.ecorea.dto.JumunDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@AllArgsConstructor
@Service
public class JumunService {
	private ProductDao productDao;
	private UserDao memberDao;
	private JumunDao jumunDao;
	
	/* 상품 -> 바로 구매 */


	public JumunDto.JumunPreview jumunList(List<Params> list, String memberId) {	
		Integer totalPrice = 0;
		List<CartDto.CartProduct> products = new ArrayList<>();
		for(Params param: list) {
			Product product = productDao.findByPno(param.getPno());
			CartDto.CartProduct jumunProduct = CartProduct.builder().pno(param.getPno()).cartpname(product.getPname())
					.pthumbnail(product.getPthumbnail()).cartcnt(param.getCnt()).price(product.getPrice()).build();
			totalPrice = totalPrice + (product.getPrice()*param.getCnt());
			products.add(jumunProduct);			
		}
		Member member = memberDao.memberFindById(memberId);
		JumunDto.JumunPreview jumunPreview = new JumunPreview(products, member.getPoint(), totalPrice, member.getName(), member.getEmail());
		return jumunPreview;		
	}
	
	
	// 주문 정보 저장
	public void newJumun(JumunInput input, JumunPreview dto, String memberId) {
		List<CartProduct> products = dto.getProducts();
		for(CartProduct product: products) {
			Integer jPrice = product.getPrice() * product.getCartcnt();
			Jumun jumun = new Jumun(null, product.getPno(), product.getCartcnt(), ShippingStatus.PAY, jPrice, input.getUsePoint(), LocalDate.now(), memberId, input.getAddressNo(), input.getShippingMsg());
			jumunDao.saveJumun(jumun);
		}		
	}
	
}
