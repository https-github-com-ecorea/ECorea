package com.project.ecorea.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.QnaDto;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;
import com.project.ecorea.service.QnaService;

import lombok.*;

@Controller
@AllArgsConstructor
public class QnaMvcController {
	
	@Autowired
	private QnaService service;
	
	/* 일반 회원 - 문의 목록 */
	@GetMapping("/mypage/member/qnaList")
	public ModelAndView memberQnaList(/*Principal principal*/String loginId) {
		return new ModelAndView("mypage/member/qnaList").addObject("memberQnaList", service.memberMyPageQuestionList(loginId));
	}
	
	/* 일반 회원 - 문의 상세 */
	@GetMapping("/mypage/member/qnaDetail")
	public ModelAndView memberQnaDetail(String loginId, Integer qqno, String imagepath) {
		return new ModelAndView("mypage/member/qnaDetail").addObject("memberQnaDetail", service.memberMypageDetail(loginId, qqno, imagepath));
	}
	
	/* 일반 회원 - 문의 등록 화면 */
	@GetMapping("/mypage/member/qnaUpload")
	public void uploadQnaQ() {
	}
	
	/* 일반 회원 - 문의 등록 */
	@PostMapping("/mypage/member/qnaUpload")
	public String uploadQnaQ(QnaDto.uploadQuestion questionUpDto) {
		service.uploadQuestion(questionUpDto);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 수정 */
	@PostMapping("/mypage/member/qnaUpdate")
	public String updateQuestion(QnaQ questionDto) {
		service.updateQuestion(questionDto);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 삭제 */
	@PostMapping("/mypage/member/qnaDelete")
	public String deleteQuestion(String loginId, Integer qqno) {
		service.deleteQuestion(loginId, qqno);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 기업 회원 - 문의 목록 */
	@GetMapping("/mypage/corp/qnaList")
	public ModelAndView corpQnaList(/*Principal principal*/String loginId) {
		return new ModelAndView("mypage/corp/qnaList").addObject("corpQnaList", service.corpMyPageQuestionList(loginId));
	}

	/* 기업 회원 - 문의 상세 화면 */
	@GetMapping("/mypage/corp/qnaDetail")
	public ModelAndView corpQnaDetail(String loginId, Integer qqno, String imagepath) {
		return new ModelAndView("mypage/corp/qnaDetail").addObject("corpQnaDetail", service.corpMypageDetail(loginId, qqno, imagepath));
	}
	
	/* 기업 회원 - 문의 답변 등록 */
	@PostMapping("/mypage/corp/qnaDetail")
	public String uploadQnaA(QnaDto.AnswerDto answerUpDto) {
		service.uploadAnswer(answerUpDto);
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 수정 */
	@PostMapping("/mypage/corp/qnaDetailUpdate")
	public String updateAnswer(QnaA answerDto) {
		service.updateAnswer(answerDto);
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 삭제 */
	@PostMapping("/mypage/corp/qnaDetailDelete")
	public String deleteAnswer(String loginId, Integer qano) {
		service.deleteAnswer(loginId, qano);
		return "redirect:/mypage/corp/qnaList";
	}
	
	
}
