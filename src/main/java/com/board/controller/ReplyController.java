package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.service.ReplyService;

@Controller
public class ReplyController {
	
	@Inject
	private ReplyService replyService;
	
	@RequestMapping(value = "/replyList", method = RequestMethod.GET)
	public String getReplyList(int bno, Model model) throws Exception {
		return reply;
	}

}
