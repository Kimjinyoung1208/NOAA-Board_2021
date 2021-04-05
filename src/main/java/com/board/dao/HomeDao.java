package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.HomeDto;

@Repository("HomeDao")
public class HomeDao {
	
	  @Autowired 
	  private SqlSession sqlSession;
	  
	  private static String namespace = "com.board.mapper.Home";
	  
	  public List<HomeDto> list() throws Exception { 
		  return sqlSession.selectList(namespace+".list"); 
	  }
	 

}
