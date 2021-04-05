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

}
