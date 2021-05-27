package com.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.dao.Vue_HomeDao;
import com.board.dto.FileDto;

@Service
public class Vue_HomeService {
	
	@Autowired
	private Vue_HomeDao vue_homeDao;
	
	public List<FileDto> list() throws Exception {
		return vue_homeDao.list();
	}
	
	public FileDto detail(int bno) throws Exception {
		return vue_homeDao.detail(bno);
	}

}
