package com.project.ecorea.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.project.ecorea.dao.UserDao;
import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	private UserDao dao;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String id = request.getParameter("id");
		
		Member member = dao.memberFindById(id);
		Corp corp = dao.corpFindById(id);
		
		HttpSession session = request.getSession();
		if(member==null) {
			session.setAttribute("msg", "사용자를 찾을 수 없습니다");
		} else {
			if(exception instanceof BadCredentialsException) {
				Integer loginFailCnt = member.getFailcnt();
				loginFailCnt++;
				
				if(loginFailCnt<5) {
					session.setAttribute("msg", "비밀번호가 " + loginFailCnt + "회 틀렸습니다. 5회 틀리면 계정이 비활성화됩니다");
					dao.memberInfoUpdate(Member.builder().id(id).failcnt(loginFailCnt).build());
				} else {
					session.setAttribute("msg", "비밀번호가 5회 틀렸습니다. 계정이 비활성화되었습니다");
					dao.memberInfoUpdate(Member.builder().id(id).failcnt(loginFailCnt).build());
				}
			} else if(exception instanceof DisabledException) {
				session.setAttribute("msg", "비활성화된 계정입니다. 관리자에게 연락하세요");
			}
		}
		
		if(corp==null) {
			session.setAttribute("msg", "사용자를 찾을 수 없습니다");
		} else {
			if(exception instanceof BadCredentialsException) {
				Integer loginFailCnt = corp.getFailcnt();
				loginFailCnt++;
				
				if(loginFailCnt<5) {
					session.setAttribute("msg", "비밀번호가 " + loginFailCnt + "회 틀렸습니다. 5회 틀리면 계정이 비활성화됩니다");
					dao.memberInfoUpdate(Member.builder().id(id).failcnt(loginFailCnt).build());
				} else {
					session.setAttribute("msg", "비밀번호가 5회 틀렸습니다. 계정이 비활성화되었습니다");
					dao.memberInfoUpdate(Member.builder().id(id).failcnt(loginFailCnt).build());
				}
			} else if(exception instanceof DisabledException) {
				session.setAttribute("msg", "비활성화된 계정입니다. 관리자에게 연락하세요");
			}
		}
		
		new DefaultRedirectStrategy().sendRedirect(request, response, "/general/login");
	}
}
