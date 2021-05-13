package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.FileDto;
import com.board.dto.HomeDto;

@Repository("HomeDao")
public class HomeDao {
	
	  @Autowired 
	  private SqlSession sqlSession;
	  
	  private static String namespace = "com.board.mapper.Home";
	  
	  public List<HomeDto> list() throws Exception { 
		  return sqlSession.selectList(namespace + ".list"); 
	  }
	  
	  public void write(FileDto fileDto) throws Exception {
		  sqlSession.insert(namespace + ".write", fileDto);
	  }
	  
	  public void writeFile(Map<String, Object> map) throws Exception {
		  sqlSession.insert(namespace + ".writeFile", map);
	  }
	  
	  public FileDto detail(int bno) throws Exception {
		  return sqlSession.selectOne(namespace + ".detail", bno);
	  }
	  
	  public void update(HomeDto homeDto) throws Exception {
		  sqlSession.update(namespace + ".update", homeDto);
	  }
	  
	  public void delete(int bno) throws Exception {
		  sqlSession.delete(namespace + ".delete", bno);
	  }
	  
	  public int count(String searchOption, String searchTxt) throws Exception {
		  HashMap<String, Object> data = new HashMap<String, Object>();
		  
		  data.put("searchOption", searchOption);
		  data.put("search", searchTxt);
		  
		  return sqlSession.selectOne(namespace + ".count", data);
	  }
	  
	  public List<HomeDto> paging(int postNum, int displayPost, String sortOption, String searchOption, String search) throws Exception {
		  HashMap<String, Object> data = new HashMap<String, Object>();
		  
		  data.put("postNum", postNum);
		  data.put("displayPost", displayPost);
		  data.put("sortOption", sortOption);
		  data.put("searchOption", searchOption);
		  data.put("search", search);
		  
		  return sqlSession.selectList(namespace + ".paging", data);
	  }
	  
	  public FileDto fileDownload(int bno) throws Exception {
		  return sqlSession.selectOne(namespace + ".fileDownload", bno);
	  }
	  
	  public void viewCount(int bno) throws Exception {
		  sqlSession.update(namespace + ".viewCount", bno);
	  }
	  
}
