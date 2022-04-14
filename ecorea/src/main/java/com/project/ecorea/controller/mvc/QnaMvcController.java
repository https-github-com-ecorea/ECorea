package com.project.ecorea.controller.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping("/mypage/member/qnaList")
	public void memberQnaList(Model model, Criteria cri) {
		log.info("memberMyPageQuestionList");
		model.addAttribute("memberQnaList", service.memberMyPageQuestionList("haramiee", cri));
		int total = service.getMemberTotal("haramiee");
		PageMakerDto pageMaker = new PageMakerDto(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/* 일반 회원 - 문의 상세 */
	@GetMapping("/mypage/member/qnaDetail")
	public ModelAndView memberQnaDetail(String loginId, Integer qqno, HttpSession session) {
		session.setAttribute("memberId", loginId);
		return new ModelAndView("mypage/member/qnaDetail").addObject("memberQnaDetail", service.memberMypageDetail("haramiee", qqno));
	}
	
	/* 일반 회원 - 문의 등록 화면 */
	@GetMapping("/mypage/member/qnaUpload")
	public void uploadQnaQ() {
	}
	
	/* 일반 회원 - 문의 등록 */
	@PostMapping("/mypage/member/qnaUpload")
	public String uploadQnaQ(QnaDto.uploadQuestion questionUpDto, String loginId, Integer pno) {
		service.uploadQuestion(questionUpDto, "haramiee", 12);
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
	@PostMapping("/mypage/member/qnaUpdate")
	public String updateQuestion(QnaQ questionDto) {
		service.updateQuestion(questionDto);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 삭제 */
	@PostMapping("/mypage/member/qnaDelete")
	public String deleteQuestion(String loginId, Integer qqno, HttpSession session) {
		session.setAttribute("session", session);
		service.deleteQuestion("haramiee", qqno);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 기업 회원 - 문의 목록 
	@GetMapping("/mypage/corp/qnaList")
	public ModelAndView corpQnaList(/*Principal principalString loginId, Criteria cri) {
		return new ModelAndView("mypage/corp/qnaList").addObject("corpQnaList", service.corpMyPageQuestionList("LG", cri));
	}
	*/
	
	/* 기업 회원 - 문의 목록 */
	@GetMapping("/mypage/corp/qnaList")
	public void corpQnaList(Model model, Criteria cri) {
		log.info("corpMyPageQuestionList");
		model.addAttribute("corpQnaList", service.corpMyPageQuestionList("아나바다", cri));
		int total = service.getCorpTotal("녹색당");
		PageMakerDto pageMaker = new PageMakerDto(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}

	/* 기업 회원 - 문의 상세 화면 */
	@GetMapping("/mypage/corp/qnaDetail")
	public ModelAndView corpQnaDetail(String loginId, Integer qqno) {
		return new ModelAndView("mypage/corp/qnaDetail").addObject("corpQnaDetail", service.corpMypageDetail("아나바다", qqno));
	}
	
	/* 기업 회원 - 문의 답변 등록 */
	@PostMapping("/mypage/corp/answerUpload")
	public String uploadQnaA(QnaDto.AnswerDto answerUpDto, Integer pno, String loginId) {
		service.uploadAnswer(answerUpDto, pno, "아나바다");
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 수정 */
	@PostMapping("/mypage/corp/answerUpdate")
	public String updateAnswer(QnaA answerDto) {
		service.updateAnswer(answerDto);
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 삭제 */
	@PostMapping("/mypage/corp/answerDelete")
	public String deleteAnswer(String loginId, Integer qano) {
		service.deleteAnswer("아나바다", qano);
		return "redirect:/mypage/corp/qnaList";
	}
	
}
