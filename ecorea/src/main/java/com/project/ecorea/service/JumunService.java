package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.dto.JumunDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@AllArgsConstructor
@Service
public class JumunService {
	private ProductDao productDao;
	
	
	public JumunDto.JumunSheet jumunList(List<Params> list, String memberId) {
		List<JumunDto.JumunSheet> jumunSheets = new ArrayList<>();
		for(Params param: list) {
			Product product = productDao.findByPno(param.getPno());
			JumunDto.JumunSheet jumunSheets = new JumunSheet(null, null, null, memberId, memberId, memberId, memberId, memberId, memberId, memberId, memberId);
					s;
			
		}
	}
}
