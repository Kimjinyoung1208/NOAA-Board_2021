package com.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.MemberDto;

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
	
	public boolean loginCheck(MemberDto memberDto) throws Exception {
		String name = sqlSession.selectOne(namespace + ".loginCheck", memberDto);
		return (name == null) ? false : true;
	}
	
	public MemberDto viewMember(MemberDto memberDto) throws Exception {
		return sqlSession.selectOne(namespace + ".viewMember", memberDto);
	}
	
}
