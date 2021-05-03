package com.board.service;

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
	
	public boolean loginCheck(MemberDto memberDto, HttpSession session) throws Exception {
		boolean result = memberDao.loginCheck(memberDto);
		if(result) {
			MemberDto memberDto2 = viewMember(memberDto);
			session.setAttribute("mId", memberDto2.getmId());
			session.setAttribute("mName", memberDto2.getmName());
		}
		
		return result;
	}
	
	public MemberDto viewMember(MemberDto memberDto) throws Exception {
		return memberDao.viewMember(memberDto);
	}

}
