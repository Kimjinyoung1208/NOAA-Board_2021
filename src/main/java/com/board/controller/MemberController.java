package com.board.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		if(memberDto.getmPw().equals("")) memberDto.setmPw(null);
		int result = memberService.join(memberDto);
		
		if(result >= 0) {
			resultTxt = "회원가입 성공";
		}
		
		memberDto.setResultText(resultTxt);
		
		return memberDto;
	}

}
