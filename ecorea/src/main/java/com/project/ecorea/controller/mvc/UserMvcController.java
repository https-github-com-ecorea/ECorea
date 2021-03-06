package com.project.ecorea.controller.mvc;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.ecorea.dto.CorpDto;
import com.project.ecorea.dto.MemberDto;
import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;
import com.project.ecorea.service.ProductService;
import com.project.ecorea.service.UserService;

import lombok.AllArgsConstructor;

@Validated
@AllArgsConstructor
@Controller
public class UserMvcController {
	private UserService service;
	private ProductService productService;
	
	/* 메인 페이지 */
	@GetMapping("/")
	public ModelAndView main() {
		return new ModelAndView("index").addObject("list", productService.productList());
	}
	
	/* 어바웃 페이지 */
	@GetMapping("/about")
	public void readAbout(Model model, HttpServletRequest request) {
		if (request.isUserInRole("ROLE_MEMBER")) {
			model.addAttribute("role", "ROLE_MEMBER");
		} else if (request.isUserInRole("ROLE_CORP")){
			model.addAttribute("role", "ROLE_CORP");
		}
	}
	
	/* 일반 회원 , 기업 회원 가입 선택 페이지 */
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/joinSelect")
	public String joinSelect(HttpSession session) {
		session.setAttribute("login", null);
		return "/general/joinSelect";
	}
	
	// 일반회원가입 화면 이동
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/memberJoin")
	public void memberJoin() {}
	
	// 일반회원가입 신청 시 가입 처리
	@PreAuthorize("isAnonymous()")
	@PostMapping("/general/memberJoin")
	public String memberJoin(@Valid MemberDto.Join dto, BindingResult bindingResult) {
		service.memberJoin(dto);
		return "redirect:/general/checkcode";
	}
	
	// 기업회원가입 화면 이동
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/corpJoin")
	public void corpJoin() {}
	
	// 기업회원가입 신청 시 가입 처리
	@PreAuthorize("isAnonymous()")
	@PostMapping("/general/corpJoin")
	public String corpJoin(@Valid CorpDto.Join dto, BindingResult bindingResult) {
		service.corpJoin(dto);
		
		return "redirect:/general/login";
	}
	
	// 일반회원 가입 시 본인 확인을 위한 이메일 체크코드 확인 페이지 이동
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/checkcode")
	public void checkcodeJoin() {}
	
	// 입력한 체크코드가 맞는지 확인
	@PreAuthorize("isAnonymous()")
	@PostMapping("/general/checkcode")
	public String checkcodeJoin(String checkcode, RedirectAttributes ra) {
		Boolean result = service.checkcode(checkcode);
		
		if(result==true) {
			ra.addFlashAttribute("msg", "회원가입이 완료되었습니다");
			return "redirect:/general/login";
		} else {
			ra.addFlashAttribute("msg", "체크코드를 확인해주세요");
			return "redirect:/general/checkcode";
		}
	}
	
	// 일반/기업 통합 회원 로그인 페이지 이동
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/login")
	public ModelAndView login(HttpSession session) {
		return new ModelAndView("general/login").addObject("login", session.getAttribute("login"));
	}
	
	// 아이디 찾기 페이지로 이동 (아이디 찾기 처리는 RestController)
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/findId")
	public void findUserId() {}
	
	// 비밀번호 재발급 페이지 이동
	@PreAuthorize("isAnonymous()")
	@GetMapping("/general/findPassword")
	public void resetUserPw() {}
	
	// 일반회원정보보기 페이지로 이동
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage/member/info")
	public ModelAndView memberInfo(Principal principal, HttpSession session) {
		if(session.getAttribute("isPasswordCheck") == null) {
			return new ModelAndView("mypage/checkPassword");
		}
		MemberDto.Info dto = service.memberInfo(principal.getName());
		
		return new ModelAndView("mypage/member/info").addObject("member",dto).addObject("role", "ROLE_MEMBER");
	}
	
	// 일반회원정보 수정
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/mypage/member/info")
	public String memberInfoUpdate(MemberDto.InfoUpdate dto, Principal principal, RedirectAttributes ra) {
		
		if(dto.getPw()==null || dto.getPw().equals("")) {
			ra.addFlashAttribute("msg", "변경할 값이 없습니다");
			return "redirect:/";
		}
		
		ra.addFlashAttribute("msg", "회원정보가 변경되었습니다");
		service.memberInfoUpdate(dto, principal.getName());
		return "redirect:/mypage/member/info";
	}
	
	// 일반회원 탈퇴
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/mypage/member/delete")
	public String memberInfoDelete(Authentication authentication, SecurityContextLogoutHandler logoutHandler, HttpServletRequest request, HttpServletResponse response) {
		service.memberInfoDelete(authentication.getName());
		
		logoutHandler.logout(request, response, authentication);
		return "redirect:/general/login";
	}
	
	// 기업회원정보보기 페이지로 이동
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage/corp/info")
	public ModelAndView corpInfo(Principal principal, HttpSession session) {
		if(session.getAttribute("isPasswordCheck") == null) {
			return new ModelAndView("mypage/checkPassword");
		}
		
		CorpDto.Info dto = service.corpInfo(principal.getName());
		
		return new ModelAndView("mypage/corp/info").addObject("corp", dto).addObject("role", "ROLE_CORP");
	}
	
	// 기업회원정보 수정
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/mypage/corp/info")
	public String corpInfoUpdate(CorpDto.infoUpdate dto, Principal principal, RedirectAttributes ra) {
		
		if(dto.getPw()==null || dto.getPw().equals("")) {
			ra.addFlashAttribute("msg", "변경할 값이 없습니다");
			return "redirect:/";
		}
		
		ra.addFlashAttribute("msg", "회원정보가 변경되었습니다");
		service.corpInfoUpdate(dto, principal.getName());
		return "redirect:/mypage/corp/info";
	}
	
	// 회원정보 페이지 이동 전 비밀번호 확인 페이지
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage/checkPassword")
	public void checkPassword() {}
	
	// 비밀번호 확인
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/mypage/checkPassword")
	public String checkPassword(Principal principal, String pw, HttpSession session, RedirectAttributes ra) {
		Boolean result = service.userCheckPassword(principal.getName(), pw);
		System.out.println(result);
		
		if(result==false) {
			ra.addFlashAttribute("failPassword", "비밀번호를 확인해주세요");
			return "redirect:/mypage/checkPassword?error";
		}
		session.setAttribute("isPasswordCheck", true);
		Member member = service.MemberCheck(principal.getName());
		if(member!=null) {
			return "redirect:/mypage/member/info";
		} else {
			Corp corp = service.CorpCheck(principal.getName());
			return "redirect:/mypage/corp/info";
		}
	}
}