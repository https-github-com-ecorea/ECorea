package com.project.ecorea.dto;

import lombok.Data;

@Data
public class PageMakerDto {
	/* PageMakerDto : 페이지 정보 */

	private int start; /* 화면에 표시되는 페이지 시작 번호 */
	private int end; /* 화면에 표시되는 페이지 마지막 번호 */
	private boolean prev; /* 이전 페이지 존재 유무 */
	private boolean next; /* 다음 페이지 존재 유무 */
	private int total; /* 전체 게시물 수 */
	private Criteria cri; /* 현재 페이지 정보 */

	public PageMakerDto(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		/* 화면에 표시할 페이지 개수를 상수로 넣어 줌 */
		this.end = (int)(Math.ceil(cri.getNowPage() / 5.0)) * 5;
		this.start = this.end - 4;
		
		/* 전체의 마지막 페이지 */
		int realEnd = (int)(Math.ceil(total * 1.0 / cri.getAmount()));
		
		if (realEnd < this.end)
			this.end = realEnd;
		
		this.prev = this.start > 1;
		this.next = this.end < realEnd;
	}
	
}
