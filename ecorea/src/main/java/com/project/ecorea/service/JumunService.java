package com.project.ecorea.service;

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
	
	/* 상품 -> 바로 구매 */
	public JumunDto.JumunPreview jumunOne(Integer pno, Integer count, String memberId) {
		Product product = productDao.findByPno(pno);
		System.out.println(product);
		List<CartDto.CartProduct> products = new ArrayList<>();
		Integer totalPrice = product.getPrice() * count;
		products.add(new CartProduct(pno, product.getPname(), count, totalPrice, memberId));
		Member member = memberDao.memberFindById(memberId);
		Address address = addressDao.defaultAddress(memberId);
		JumunDto.JumunPreview jumunPreview = new JumunPreview(products, member.getPoint(), totalPrice, member.getName(), member.getEmail(), address);
		return jumunPreview;
	}

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
