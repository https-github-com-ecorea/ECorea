package com.project.ecorea.service;

import java.io.*;
import java.util.*;

import javax.validation.*;

import org.apache.commons.lang3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.dto.BookmarkDto.*;

import lombok.*;

import com.project.ecorea.dao.*;

import com.project.ecorea.entity.*;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	/* Property 읽어 오기 */
	@Value("${upload.image.path}") /* 경로 */
	private String imagePath;
	@Value("${upload.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
	private final ProductDao productDao;
	private final HugiDao hugiDao;
	private final QnaDao qnaDao;
	private final CartDao cartDao;
	private final CartService cartService;
	private final QnaService qnaService;
	
	/* 상품 개수 */
	public int getTotal() {
		return productDao.getTotal();
	}
	
	/* 전체 상품 개수 */
	public int getCategoryTotal(String catecode) {
		return productDao.getCategoryTotal(catecode);
	}
	
	/* 상품 목록 */
	public List<ProductDto.productList> productList() {		
		List<ProductDto.productList> productList = new ArrayList<>();
		List<ProductDto.productList> entity = productDao.productList();
		for (ProductDto.productList product : entity) {
			product.setPthumbnail(imagePath + product.getPthumbnail());
			productList.add(product);
		}
		return productList;
	}
	
	/* 상품 목록 (페이징 적용) */
	public List<ProductDto.productList> productPagingList(Criteria cri) {
		List<ProductDto.productList> productList = new ArrayList<>();
		List<ProductDto.productList> newList = productDao.productPagingList(cri);
		for (ProductDto.productList element : newList) {
			element.setPthumbnail(imagePath + element.getPthumbnail());
			productList.add(element);
		}
		return productList;
	}
	
	/* 상품 상세 페이지 */
	public ProductDto.ProductRead productRead(Integer pno) {
		ProductDto.ProductRead productDto = productDao.findByPno(pno).toDto(imagePath);
		productDto.setHugis(hugiDao.findByPno(pno));
		productDto.setQQnas(qnaService.productQuestionList(pno, imagePath));
		return productDto;
	}
	
	/* 재고 확인 (수량 변경 시 필요) */
	public Boolean checkStock(Integer pno, Integer count) {
		return productDao.findByPno(pno).getPstock() >= count;
	}

	/* 상품 등록 */
	public void uploadProduct(ProductDto.Upload uploadDto) {
		Product product = uploadDto.toEntity();
		MultipartFile pthumbnail = uploadDto.getPthumbnail();
		product.setPthumbnail(defaultImage);
		if(pthumbnail!=null && pthumbnail.isEmpty()==false && pthumbnail.getContentType().toLowerCase().startsWith("image/")) {
			String pthumbnailName = UUID.randomUUID() + "-" + pthumbnail.getOriginalFilename();
			File file = new File(imageFolder, pthumbnailName);
			try {
				pthumbnail.transferTo(file);
				product.setPthumbnail(pthumbnailName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		productDao.save(product);
	}

	// 등록 상품 리스트 출력
	public List<ProductDto.CorpProductList> regProductsList(String corpId) {
		List<ProductDto.CorpProductList> corpProductListDto = productDao.findByCorpId(corpId, imagePath);
		return corpProductListDto;
	}
	
	// 등록된 상품 상세정보 출력
	public ProductDto.UpdateProduct readProductDetailForUpdate(String corpId, Integer pno) {
		ProductDto.UpdateProduct product = productDao.findByCorpIdAndPno(corpId, pno);
		return product;
	}
	
	// 상품 상세정보 수정
	public Boolean updateProduct(@Valid ProductDto.UpdateProduct productDto) {
		// content 내용이 바뀌었는지 확인
		ProductDto.UpdateProduct existInfo = productDao.findByCorpIdAndPno(productDto.getCorpId(), productDto.getPno());
		Boolean isNotChange = productDto.getPcontent().equals(existInfo.getPcontent());
		if(isNotChange)
			productDto.setPcontent("");		
		// 커맨드객체 "" -> update시 null 가질 수 있도록
		Product product = productDto.toEntity();
		// 변경사항이 없을 경우
		if(product.getPname()==null && product.getPrice()==null && product.getPstock()==null 
				&& product.getPthumbnail()==null && product.getPcontent()==null) {
			return false;
		}	
		product.setPno(productDto.getPno());
		product.setCorpId(productDto.getCorpId());
		productDao.updateProduct(product);
		return true;
	}
	
	// 상품 삭제
	public Boolean deleteProduct(String corpId, Integer pno) {
		Integer deleteResult = productDao.deleteProduct(corpId, pno);
		if(deleteResult<=0) {
			return false;
		}
		return true;
	}
	
	// 장바구니에 한 상품 한 개 담기
	public Boolean shoppingCartOne(Integer pno, String memberId) {
		// cart에 이미 담겨있는 상품인지 확인 
		// 담겨있으면 수량 1증가, 아니면 saveOneProduct		
		Integer cartcnt = 1;
		Cart existProduct = cartDao.findByMemberIdAndPno(memberId, pno);
		if(existProduct==null) {
			Product product = productDao.findByPno(pno);
			Integer cartPrice = product.getPrice()*cartcnt;
			Cart cart = Cart.builder().memberId(memberId).pno(pno).cartcnt(cartcnt)
					.cartpname(product.getPname()).cartprice(cartPrice).build();
			if(cart==null) {
				return false;
			} else {
				cartDao.saveOneProduct(cart);
				return true;
			}			
		} else {
			cartService.plusCnt(memberId, pno);
			return true;
		}
	}

	/* 장바구니에 한 상품 여러 개 담기 */
	public Boolean shoppingCartMultiple(Integer pno, Integer count, String memberId) {
		Cart isExistProductInCart = cartDao.findByMemberIdAndPno(memberId, pno);
		if (isExistProductInCart == null) {
			Product product = productDao.findByPno(pno);
			Integer cartPrice = product.getPrice() * count;
			Cart cart = Cart.builder().memberId(memberId).pno(pno).cartcnt(count)
					.cartpname(product.getPname()).cartprice(cartPrice).build();
			cartDao.saveOneProduct(cart);
			return true;
		} else {
			cartService.plusCnt(memberId, pno);
			return true;
		}
	}

	// 관심상품 -> 장바구니 선택한 상품 담기
	public Boolean shoppingCartSelected(PnoSelected dto, String memberId) {
		Integer cartcnt = 1;
		List<Integer> pnos = dto.getPnos();
		for(Integer pno: pnos) {
			Cart existProduct = cartDao.findByMemberIdAndPno(memberId, pno);
			if(existProduct==null) {
				Product product = productDao.findByPno(pno);
				Integer cartPrice = product.getPrice()*cartcnt;
				Cart cart = Cart.builder().memberId(memberId).pno(pno).cartcnt(cartcnt)
						.cartpname(product.getPname()).cartprice(cartPrice).build();
				if(cart==null) {
					return false;
				} else {
					cartDao.saveOneProduct(cart);
				}
			} else {
				cartService.plusCnt(memberId, pno);
			}
		}
		return true;
	}

}
