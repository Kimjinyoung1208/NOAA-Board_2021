package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import com.board.service.HomeService;
import com.board.service.MemberService;

@Controller
public class MemberController {
	
	@Inject
	private MemberService memberService;

}
