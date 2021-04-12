package com.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dto.FileDto;
import com.board.dto.HomeDto;
import com.board.service.HomeService;

@Controller
public class HomeController {
	
	@Inject
	private HomeService homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPaging(Model model, HttpServletRequest request) throws Exception {
		int num = ( request.getParameter("num") != null ) ? Integer.parseInt(request.getParameter("num")) : 1;
		// 게시물 갯수
		int count = homeService.count();
		// 한 페이지 당 게시물 갯수
		int postNum = 10;
		// 페이지 갯수
		int pageNum = (int)Math.ceil((double)count / postNum);
		// 출력할 게시물
		int displayPost = (num - 1) * postNum;
		// 한번에 표시할 페이지 갯수
		int pageNum_cnt = 10;
		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		// 마지막 번호 재계산
		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
		
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}
		
		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
		
		List<HomeDto> list = null;
		list = homeService.paging(postNum, displayPost);
		
		try {
			model.addAttribute("list", list);
			model.addAttribute("pageNum", pageNum);
			
			// 시작 및 끝 번호
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
			
			// 이전 및 다음
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);
			
			// 현재 페이지
			model.addAttribute("select", num);
		} catch ( Exception e ) {}
		
		return "home";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(FileDto fileDto, MultipartHttpServletRequest mreq) throws Exception {
		homeService.write(fileDto, mreq);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String getDetail(Model model, int bno) throws Exception {
		HomeDto data = homeService.detail(bno);
		
		try {
			model.addAttribute("detail", data);
		} catch ( Exception e ) {}
		
		return "detail";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String getUpdate(Model model, int bno) throws Exception {
		HomeDto data = homeService.detail(bno);
		
		try {
			model.addAttribute("detail", data);
		} catch ( Exception e ) {}
		
		return "update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(HomeDto homeDto) throws Exception {
		homeService.update(homeDto);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(Model model, int bno) throws Exception {
		homeService.delete(bno);
		
		return "redirect:/";
	}

}
