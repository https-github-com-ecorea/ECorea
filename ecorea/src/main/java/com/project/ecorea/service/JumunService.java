package com.project.ecorea.service;

import java.time.*;
import java.util.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

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

	// 주문 미리보기
	public JumunDto.JumunPreview jumunPreview(List<Params> list, String memberId) {	
		Integer totalPrice = 0;
		List<CartDto.CartProduct> products = new ArrayList<>();
		for(Params param: list) {
			if(param.getPno()!=null && param.getCnt()!=null) {
				Product product = productDao.findByPno(param.getPno());
				if(param.getCnt()<=product.getPstock()) {
					CartDto.CartProduct jumunProduct = CartProduct.builder().pno(param.getPno()).cartpname(product.getPname())
							.pthumbnail(product.getPthumbnail()).cartcnt(param.getCnt()).price(product.getPrice()).build();
					totalPrice = totalPrice + (product.getPrice()*param.getCnt());
					products.add(jumunProduct);
				}				
			}						
		}
		Member member = memberDao.memberFindById(memberId);
		JumunDto.JumunPreview jumunPreview = new JumunPreview(products, member.getPoint(), totalPrice, member.getName(), member.getEmail());
		return jumunPreview;
	}
	
	
	// 주문 정보 저장
	@Transactional
	public void newJumun(JumunInput input, JumunPreview dto, String memberId) {
		List<CartProduct> products = dto.getProducts();
		Member member = memberDao.memberFindById(memberId);
		if(input.getUsePoint()<=member.getPoint()) {
			memberDao.memberUsePointUpdate(memberId, input.getUsePoint());
		}
		for(CartProduct product: products) {
			Integer jPrice = product.getPrice() * product.getCartcnt();
			Jumun jumun = new Jumun(null, product.getPno(), product.getCartcnt(), ShippingStatus.PAY, jPrice, input.getUsePoint(), LocalDate.now(), memberId, input.getAddressNo(), input.getShippingMsg());
			productDao.updateStock(product.getCartcnt(), product.getPno());
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

	// 사용 가능 포인트 확인
	public Boolean checkPoint(Integer usePoint, String id) {
		return memberDao.memberFindById(id).getPoint()>=usePoint;
	}
	
}
