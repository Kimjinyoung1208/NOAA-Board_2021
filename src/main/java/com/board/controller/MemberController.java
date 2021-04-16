package com.board.controller;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
		
//		String mId = memberDto.getmId();
//		String mPw = memberDto.getmPw();
//		String mName = memberDto.getmName();
//		String mPhone = memberDto.getmPhone();
//		String mEmail = memberDto.getmEmail();
//		
//		String pId = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
//		String pPw = "^[A-Za-z0-9]{6,12}$";
//		String pPhone = "^[0-9]+$";
//
//		Pattern patternId = Pattern.compile(pId);
//		Pattern patternPw = Pattern.compile(pId);
//		Pattern patternPhone = Pattern.compile(pId);
//		Pattern patternEmail = Pattern.compile(pEmail);
		if(memberDto.getmPw().equals("")) memberDto.setmPw(null);
		int result = memberService.join(memberDto);
		
		if(result>=0) {
			resultTxt = "회원가입 성공";
		}
		//memberService.join(memberDto);
		
		memberDto.setResultText(resultTxt);
		
		return memberDto;
	}

}
