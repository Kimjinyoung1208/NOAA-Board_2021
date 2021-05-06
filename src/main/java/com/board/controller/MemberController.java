package com.board.controller;


import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

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
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HashMap<String, Object> postLogin(@ModelAttribute MemberDto memberDto, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		MemberDto login = memberService.login(memberDto);
		
		boolean resultBool = false;
		
		if(login == null) {
			session.setAttribute("member", null);
		} else {
			session.setAttribute("member", login);
			resultBool = true;
			
		}
		resultMap.put("result",resultBool);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void postLogout(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
		memberService.logout(session);
	}

}
