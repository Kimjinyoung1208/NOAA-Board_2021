package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.ReplyDto;

@Repository("ReplyDao")
public class ReplyDao {

	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.board.mapper.ReplyMapper";
	
	public List<ReplyDto> list(int bno) throws Exception {
		return sqlSession.selectList(namespace + ".replyList", bno);
	}
	
}
