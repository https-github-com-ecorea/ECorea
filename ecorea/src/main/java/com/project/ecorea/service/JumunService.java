package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
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
	
}
