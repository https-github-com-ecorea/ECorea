package com.project.ecorea.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ecorea.dao.UserDao;
import com.project.ecorea.dto.Account;
import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoginService implements UserDetailsService {
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = dao.memberFindById(id);
		Corp corp = null;
		
		if(member==null) {
			corp = dao.corpFindById(id);
		}
		
		Account account = null;
		String authority = null;
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		if(member==null && corp==null)
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		
		if(member!=null) {
			account = Account.builder().id(id).pw(member.getPw()).enable(member.getEnable()).build();
			authority = member.getAuthority();
		} else if (corp!=null) {
			account = Account.builder().id(id).pw(corp.getPw()).enable(corp.getEnable()).build();
			authority = corp.getAuthority();
		}
		
		authorities.add(new SimpleGrantedAuthority(authority));
		account.setAuthorities(authorities);
		
		return account;
	}
}