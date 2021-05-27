package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.ReplyDao;
import com.board.dto.ReplyDto;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyDao replyDao;
	
	public List<ReplyDto> list(int bno) throws Exception {
		return replyDao.list(bno);
	}

}
