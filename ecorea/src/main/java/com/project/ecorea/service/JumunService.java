package com.project.ecorea.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.CartDto.*;
import com.project.ecorea.dto.JumunDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class JumunService {
	@Value("${upload.image.path}")
	private String imagePath;
	
	private final ProductDao productDao;
	private final UserDao memberDao;
	private final JumunDao jumunDao;
	
	/* 상품 -> 바로 구매 */
	public JumunDto.JumunPreview jumunOne(Integer pno, Integer count, String memberId) {
		Product product = productDao.findByPno(pno);
		System.out.println(product);
		List<CartDto.CartProduct> products = new ArrayList<>();
		Integer totalPrice = product.getPrice() * count;
		products.add(new CartProduct(pno, product.getPname(), count, totalPrice, memberId));
		Member member = memberDao.memberFindById(memberId);
		JumunDto.JumunPreview jumunPreview = new JumunPreview(products, member.getPoint(), totalPrice, member.getName(), member.getEmail());
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
	
	
	// 주문 정보 저장
	public void newJumun(JumunInput input, JumunPreview dto, String memberId) {
		List<CartProduct> products = dto.getProducts();
		for(CartProduct product: products) {
			Integer jPrice = product.getPrice() * product.getCartcnt();
			Jumun jumun = new Jumun(null, product.getPno(), product.getCartcnt(), ShippingStatus.PAY, jPrice, input.getUsePoint(), LocalDate.now(), memberId, input.getAddressNo(), input.getShippingMsg());
			jumunDao.saveJumun(jumun);
		}		
	}

	// 주문 정보 출력
	public List<JumunDto.JumunList> readJumunList(String memberId) {		
		List<Jumun> jumuns = jumunDao.jumunFindByMemberId(memberId);
		List<JumunDto.JumunList> jumunList = new ArrayList<>();
		
		for(Jumun jumun:jumuns) {
			String status = jumun.getJStatus().getKorean();
			JumunDto.JumunList dto = jumunDao.jumunFindByMemberIdAndJno(memberId, imagePath, jumun.getJno());
			dto.setJstatus(status);
			jumunList.add(dto);			
		}
		return jumunList;
	}
	
}
