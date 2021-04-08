package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.HomeDao;
import com.board.dto.HomeDto;

@Service
public class HomeService {
	
	@Autowired
	private HomeDao homeDao;
	
	public List<HomeDto> list() throws Exception {
		return homeDao.list();
	}
	
	public void write(HomeDto homeDto) throws Exception {
		homeDao.write(homeDto);
	}
	
	public HomeDto detail(int bno) throws Exception {
		return homeDao.detail(bno);
	}
	
	public void update(HomeDto homeDto) throws Exception {
		homeDao.update(homeDto);
	}
	
	public void delete(int bno) throws Exception {
		homeDao.delete(bno);
	}
	
	public int count() throws Exception {
		return homeDao.count();
	}
	
	public List<HomeDto> paging(int postNum, int displayPost) throws Exception {
		return homeDao.paging(postNum, displayPost);
	}
	
}
