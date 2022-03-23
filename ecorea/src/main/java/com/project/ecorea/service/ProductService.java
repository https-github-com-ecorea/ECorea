package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dto.*;

import lombok.*;

import com.project.ecorea.dao.*;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductDao productDao;
	private final HugiDao hugiDao;
	private final QnaDao qnaDao;
	
	/* 상품 목록 페이징에 필요한 상수 */
	private int PRODUCT_PER_PAGE = 16;
	private int PAGE_PER_BLOCK = 5;
	
	/* 상품 목록 페이징 */
	public PageDto productList(int pageno) {
		int count = productDao.productCnt(null);

		int firstRnum = ((pageno - 1) * PRODUCT_PER_PAGE) + 1;
		int lastRnum = (firstRnum + PRODUCT_PER_PAGE) - 1;
		if (count < lastRnum)
			lastRnum = count;
		
		List<ProductDto.productList> products = productDao.productListPaging(firstRnum, lastRnum, imagePath);
		
		int countOfPage = (count/PRODUCT_PER_PAGE) + 1;
		
		if (count % PRODUCT_PER_PAGE == 0)
			countOfPage--;

		int blockNo = pageno/PAGE_PER_BLOCK;

		if (pageno % PAGE_PER_BLOCK == 0)
			blockNo--;
		
		int start = blockNo * PAGE_PER_BLOCK + 1;
		int prev = start - 1;
		int end = start + PAGE_PER_BLOCK - 1;
		int next = end + 1;
		
		if (end >= countOfPage) {
			end = countOfPage;
			next = 0;
		}
		
		return new PageDto(prev, start, end, next, pageno, products);
	}
	
	/* 상품 상세 페이지 */
	public ProductDto.productRead productRead(Integer pno) {
		ProductDto.productRead dto = productDao.findByPno(pno).toDto(imagePath);
		dto.setReviews(hugiDao.findByPno(pno));
		return dto;
	}
	
}
