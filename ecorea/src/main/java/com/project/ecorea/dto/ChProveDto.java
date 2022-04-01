package com.project.ecorea.dto;

import java.util.*;

import lombok.*;

@Data
@AllArgsConstructor
public class ChProveDto {
	@Data
	public static class Prove {
		private Integer cpno;
		private String cpimg;
		private String cptitle;
		private String cpregday;
	}
	
	@Data
	public static class ProveList {
		private String cname;
		private List<Prove> proves;				
	}
	
}
