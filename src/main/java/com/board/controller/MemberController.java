package com.board.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.MemberDto;
import com.board.service.MemberService;

@Controller
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String getJoin() throws Exception {
		
		return "join";
	}
	
	@ResponseBody
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public MemberDto postJoin(@ModelAttribute MemberDto memberDto) throws Exception {
		String resultTxt = "회원가입 실패";
		String idCheckResult = "아이디가 중복됩니다.";
				
		if(memberDto.getmPw().equals("")) memberDto.setmPw(null);
		int result = memberService.join(memberDto);
		int idResult = memberService.idCheck(memberDto);
		
		try {
			if(idResult == 1) {
				memberDto.setIdCheckResult(idCheckResult);
				return memberDto;
			} else if(idResult == 0) {
				idCheckResult = null;
				memberDto.setIdCheckResult(idCheckResult);
				memberService.join(memberDto);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		if(result >= 0) {
			resultTxt = "회원가입 성공";
		}
		
		memberDto.setResultText(resultTxt);
		
		return memberDto;
	}
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(MemberDto memberDto) throws Exception {
		int result = memberService.idCheck(memberDto);
		return result;
	}

	@RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
	public int pwCheck(MemberDto memberDto) throws Exception {
		int result = memberService.pwCheck(memberDto);
		return result;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() throws Exception {
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public MemberDto postLogin(@ModelAttribute MemberDto memberDto, HttpSession session) throws Exception {
		String result = "로그인 실패";
		boolean resultData = memberService.loginCheck(memberDto, session);
		if(resultData == true) {
			memberDto.setLoginResult(result);
			return memberDto;
		} else {
			memberDto.setLoginResult(result);
		}
		return memberDto;
	}

}
