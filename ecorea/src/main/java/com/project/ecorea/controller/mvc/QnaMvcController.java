package com.project.ecorea.controller.mvc;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.Criteria;
import com.project.ecorea.dto.PageMakerDto;
import com.project.ecorea.dto.QnaDto;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;
import com.project.ecorea.service.QnaService;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class QnaMvcController {
	
	@Autowired
	private QnaService service;
	
	/* 일반 회원 - 문의 목록 
	@GetMapping("/mypage/member/qnaList")
	public ModelAndView memberQnaList(/*Principal principalString loginId, Criteria cri) {
		return new ModelAndView("mypage/member/qnaList").addObject("memberQnaList", service.memberMyPageQuestionList("ngoley6", cri));
	}
	*/
	
	/* 일반 회원 - 문의 목록 (페이징) */
	@Secured("ROLE_MEMBER")
	@GetMapping("/mypage/member/qnaList")
	public void memberQnaList(Model model, Criteria cri, Principal principal) {
		log.info("memberMyPageQuestionList");
		model.addAttribute("memberQnaList", service.memberMyPageQuestionList(principal.getName(), cri));
		int total = service.getMemberTotal("haramiee");
		PageMakerDto pageMaker = new PageMakerDto(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/* 일반 회원 - 문의 상세 */
	@Secured("ROLE_MEMBER")
	@GetMapping("/mypage/member/qnaDetail")
	public ModelAndView memberQnaDetail(Principal principal, Integer qqno, HttpSession session) {
		session.setAttribute("memberId", principal.getName());
		return new ModelAndView("mypage/member/qnaDetail").addObject("memberQnaDetail", service.memberMypageDetail(principal.getName(), qqno));
	}
	
	/* 일반 회원 - 문의 등록 화면 */
	@Secured("ROLE_MEMBER")
	@GetMapping("/mypage/member/qnaUpload")
	public void uploadQnaQ() {
	}
	
	/* 일반 회원 - 문의 등록 */
	@Secured("ROLE_MEMBER")
	@PostMapping("/mypage/member/qnaUpload")
	public String uploadQnaQ(QnaDto.uploadQuestion questionUpDto, Principal principal, Integer pno) {
		service.uploadQuestion(questionUpDto, principal.getName(), pno);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 수정 화면 */
	/*
	@GetMapping("/mypage/member/qnaUpdate")
	public ModelAndView updateQuestion() {
		String loginId = "ngoley6";
		Integer qqno = 3;
		return new ModelAndView().addObject("memberUpdateQuestion", service.memberMypageDetail(loginId, qqno, null));
	}
	*/
	
	/* 일반 회원 - 문의 수정 */
	@Secured("ROLE_MEMBER")
	@PostMapping("/mypage/member/qnaUpdate")
	public String updateQuestion(QnaQ questionDto) {
		service.updateQuestion(questionDto);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 삭제 */
	@Secured("ROLE_MEMBER")
	@PostMapping("/mypage/member/qnaDelete")
	public String deleteQuestion(Principal principal, Integer qqno, HttpSession session) {
		session.setAttribute("session", session);
		service.deleteQuestion(principal.getName(), qqno);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 기업 회원 - 문의 목록 
	@GetMapping("/mypage/corp/qnaList")
	public ModelAndView corpQnaList(/*Principal principalString loginId, Criteria cri) {
		return new ModelAndView("mypage/corp/qnaList").addObject("corpQnaList", service.corpMyPageQuestionList("LG", cri));
	}
	*/
	
	/* 기업 회원 - 문의 목록 */
	@Secured("ROLE_CORP")
	@GetMapping("/mypage/corp/qnaList")
	public void corpQnaList(Model model, Criteria cri, Principal principal) {
		log.info("corpMyPageQuestionList");
		model.addAttribute("corpQnaList", service.corpMyPageQuestionList(principal.getName(), cri));
		int total = service.getCorpTotal(principal.getName());
		PageMakerDto pageMaker = new PageMakerDto(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}

	/* 기업 회원 - 문의 상세 화면 */
	@Secured("ROLE_CORP")
	@GetMapping("/mypage/corp/qnaDetail")
	public ModelAndView corpQnaDetail(Principal principal, Integer qqno) {
		return new ModelAndView("mypage/corp/qnaDetail").addObject("corpQnaDetail", service.corpMypageDetail(principal.getName(), qqno));
	}
	
	/* 기업 회원 - 문의 답변 등록 */
	@Secured("ROLE_CORP")
	@PostMapping("/mypage/corp/answerUpload")
	public String uploadQnaA(QnaDto.AnswerDto answerUpDto, Integer pno, Principal principal) {
		service.uploadAnswer(answerUpDto, pno, principal.getName());
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 수정 */
	@Secured("ROLE_CORP")
	@PostMapping("/mypage/corp/answerUpdate")
	public String updateAnswer(QnaA answerDto) {
		service.updateAnswer(answerDto);
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 삭제 */
	@Secured("ROLE_CORP")
	@PostMapping("/mypage/corp/answerDelete")
	public String deleteAnswer(Principal principal, Integer qano) {
		service.deleteAnswer(principal.getName(), qano);
		return "redirect:/mypage/corp/qnaList";
	}
	
}
