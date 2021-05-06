package com.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public int join(MemberDto memberDto) throws Exception {
		return memberDao.join(memberDto);
	}
	
	public int idCheck(MemberDto memberDto) throws Exception {
		int result = memberDao.idCheck(memberDto);
		
		return result;
	}

	public int pwCheck(MemberDto memberDto) throws Exception {
		int result = memberDao.pwCheck(memberDto);
		
		return result;
	}
	
	public MemberDto login(MemberDto memberDto) throws Exception {
		return memberDao.login(memberDto);
	}
	
	public MemberDto viewMember(MemberDto memberDto) throws Exception {
		return memberDao.viewMember(memberDto);
	}
	
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
