package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.FileDto;

@Repository("Vue_HomeDao")
public class Vue_HomeDao {

	 @Autowired 
	  private SqlSession sqlSession;
	 
	 private static String namespace = "com.board.mapper.Home";
	 
	 public List<FileDto> list() throws Exception { 
		  return sqlSession.selectList(namespace + ".list"); 
	  }
	 
	 public FileDto detail(int bno) throws Exception {
		 return sqlSession.selectOne(namespace + ".detail", bno);
	 }
	
}
