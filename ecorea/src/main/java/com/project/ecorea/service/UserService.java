package com.project.ecorea.service;

import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ecorea.dao.UserDao;
import com.project.ecorea.dto.CorpDto;
import com.project.ecorea.dto.MemberDto;
import com.project.ecorea.dto.MemberDto.Info;
import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;


@Service
public class UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender javaMailSender;
	
	// 유저에게 메일을 보내주기 위한 JavaMailSender 설정
	private void sendMail(String from, String to, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	// 일반회원가입
	public void memberJoin(MemberDto.Join dto) {
		Member member = dto.toEntity();
		String checkcode = RandomStringUtils.randomAlphanumeric(20);
		String changePassword = passwordEncoder.encode(member.getPw());
		member.setPw(changePassword).setEnable(false).setAuthority("ROLE_MEMBER").setPoint(0).setCheckcode(checkcode).setJoinday(LocalDate.now()).setFailcnt(0);
		dao.memberSave(member);
		String text = new StringBuilder("<h1>가입확인 코드</h1>")
				.append("<p>가입을 마무리하려면 아래 코드를 화면에 입력하세요</p>")
				.append(checkcode).toString();
		sendMail("admin@icia.com", member.getEmail(), "가입확인 메일", text);
	}
	
	// 기업회원가입
	public void corpJoin(CorpDto.Join dto) {
		Corp corp = dto.toEntity();
		String changePassword = passwordEncoder.encode(dto.getPw());
		corp.setPw(changePassword).setJoinday(LocalDate.now()).setEnable(true).setAuthority("ROLE_CORP").setFailcnt(0);
		
		dao.corpSave(corp);
	}

	// 일반회원 체크코드 확인 및 계정 활성화
	public Boolean checkcode(String checkcode) {
		Member member = dao.findByCheckcode(checkcode);
		if(member==null)
			return false;
		return dao.memberInfoUpdate(Member.builder().id(member.getId()).checkcode("0").enable(true).build())==1;
	}

	// 회원 아이디 찾기
	@Transactional(readOnly = true)
	public String findUserId(String name, String email) {
		Member member = dao.memberFindByEmailAndName(email, name);
		Corp corp = null;
		
		
		if(member==null) {
			corp = dao.corpFindByEmailAndName(email, name);
			
			if(corp!=null)
				return corp.getId();
		} else {
			return member.getId();
		}
		
		return null;
	}
	
	// 비밀번호 초기화
	public Boolean resetUserPw(String id, String email) {
		System.out.println("===========================");
		Member member = dao.memberFindByIdAndEmail(id, email);
		Corp corp = null;
		String tempPassword = null;
		String EncodedtempPassword = null;
		
		if(member==null) {
			corp = dao.corpFindByIdAndEmail(id, email);
			
			if(corp==null) {
				return false;
			} else if(corp!=null) {
				tempPassword = RandomStringUtils.randomAlphanumeric(20);
				EncodedtempPassword = passwordEncoder.encode(tempPassword);
				dao.corpInfoUpdate(Corp.builder().id(id).pw(EncodedtempPassword).build());
			}
		} else if(member!=null) {
			tempPassword = RandomStringUtils.randomAlphanumeric(20);
			EncodedtempPassword = passwordEncoder.encode(tempPassword);
			dao.memberInfoUpdate(Member.builder().id(id).pw(EncodedtempPassword).build());			
		}
		
		String text = new StringBuilder("<h1>임시비밀 번호</h1>")
				.append("<p>아래 임시 비밀번호로 로그인하세요. 로그인 후 비밀번호를 변경해 주세요</p>")
				.append(tempPassword).toString();
		sendMail("admin@icia.com", email, "임시비밀번호 안내", text);
		
		return true;
	}
	
	// 일반회원 정보 변경
	public void memberInfoUpdate(MemberDto.InfoUpdate dto, String loginId) {
		Member member = dto.toEntity();
		String changePassword = passwordEncoder.encode(member.getPw());
		member.setId(loginId).setPw(changePassword);
		
		dao.memberInfoUpdate(member);
	}

	// 기업회원 정보 변경
	public void corpInfoUpdate(CorpDto.infoUpdate dto, String loginId) {
		Corp corp = dto.toEntity();
		String changePassword = passwordEncoder.encode(corp.getPw());
		corp.setId(loginId).setPw(changePassword);
		
		dao.corpInfoUpdate(corp);
	}

	// 일반 회원 정보 보기
	public MemberDto.Info memberInfo(String loginId) {
		return dao.memberFindById(loginId).toInfo();
	}

	// 일반 회원 탈퇴
	public Boolean memberInfoDelete(String loginId) {
		if(dao.memberDeleteById(loginId)==true) {
			return true;
		} else {
			return false;
		}
	}

	// 기업 회원 정보 보기
	public CorpDto.Info corpInfo(String loginId) {
		return dao.corpFindById(loginId).toInfo();
	}

	// 회원 정보 페이지 진입 전 비밀번호 확인
	public Boolean userCheckPassword(String loginId, String pw) {
		Member member = dao.memberFindById(loginId);
		Corp corp = null;
		if(member==null) {
			corp = dao.corpFindById(loginId);
			
			if(corp!=null)
				return passwordEncoder.matches(pw, corp.getPw());
		} else {
			return passwordEncoder.matches(pw, member.getPw());			
		}
		return false;
	}

	// 일반회원인지 확인해주는 코드
	public Member MemberCheck(String loginId) {
		Member member = dao.memberFindById(loginId);
		if(member!=null) {
			return member;			
		} else {
			return null;
		}
	}

	// 기업회원인지를 확인해주는 코드
	public Corp CorpCheck(String loginId) {
		Corp corp = dao.corpFindById(loginId);
		if(corp!=null) {
			return corp;
		} else {
			return null;
		}
	}

	public Boolean findOverlapId(String id) {
		Member member = dao.memberFindById(id);
		Corp corp = null;
		
		
		if(member==null) {
			corp = dao.corpFindById(id);
			
			if(corp!=null)
				return false;
		} else {
			return false;
		}
		
		return true;
	}
}