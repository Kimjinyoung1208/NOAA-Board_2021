package com.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.MemberDto;
import com.board.service.MemberService;

@Repository("MemberDao")
public class MemberDao {

	@Autowired 
	private SqlSession sqlSession;
	
	private static String namespace = "com.board.mapper.Member";
	
	public int join(MemberDto memberDto) throws Exception {
		return sqlSession.insert(namespace + ".join", memberDto);
	}
	
	public int idCheck(MemberDto memberDto) throws Exception {
		int result = sqlSession.selectOne(namespace + ".idCheck", memberDto);
		return result;
	}

	public int pwCheck(MemberDto memberDto) throws Exception {
		int result = sqlSession.selectOne(namespace + ".pwCheck", memberDto);
		return result;
	}
	
}
