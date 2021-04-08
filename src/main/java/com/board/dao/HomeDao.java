package com.board.dao;

import java.util.HashMap;
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
		  return sqlSession.selectList(namespace + ".list"); 
	  }
	  
	  public void write(HomeDto homeDto) throws Exception {
		  sqlSession.insert(namespace + ".write", homeDto);
	  }
	  
	  public HomeDto detail(int bno) throws Exception {
		  return sqlSession.selectOne(namespace + ".detail", bno);
	  }
	  
	  public void update(HomeDto homeDto) throws Exception {
		  sqlSession.update(namespace + ".update", homeDto);
	  }
	  
	  public void delete(int bno) throws Exception {
		  sqlSession.delete(namespace + ".delete", bno);
	  }
	  
	  public int count() throws Exception {
		  return sqlSession.selectOne(namespace + ".count");
	  }
	  
	  public List<HomeDto> paging(int postNum, int displayPost) throws Exception {
		  HashMap<String, Integer> data = new HashMap<String, Integer>();
		  
		  data.put("postNum", postNum);
		  data.put("displayPost", displayPost);
		  
		  return sqlSession.selectList(namespace + ".paging", data);
	  }
	  
}
