package com.project.ecorea.dto;

import java.util.*;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkDto {
	
	@Data
	@AllArgsConstructor
	public static class BookmarkList {
		private Integer pno;
		private String catecode;
		private String pname;
		private Integer price;		
		private String pthumbnail;		
	}	
	
	@Data
	@AllArgsConstructor
	public static class PnoSelected {
		private List<Integer> pnos;
	}
}
