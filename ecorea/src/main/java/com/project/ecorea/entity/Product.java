package com.project.ecorea.entity;

import java.time.*;

import org.springframework.format.annotation.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class Product {
	private Integer pno;
	private String pcategory;
	private Integer price;
	private String pname;
	private Integer pstock;
	private String pcontent;
	private String pthumbnail;
	private Integer pordercnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pregday;
	private String corpId;	
}
