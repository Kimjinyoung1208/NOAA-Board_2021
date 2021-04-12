package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
	public String postWrite(HomeDto homeDto) throws Exception {
		MultipartFile uploadFile = homeDto.getUploadFile();
		
		if ( uploadFile != null ) {
			String fileName = uploadFile.getOriginalFilename();
			homeDto.setFileName(fileName);
			
			try {
				File file = new File("C:/fileUpload/" + fileName);
				uploadFile.transferTo(file);
			} catch (IOException e) {}
		}
		
		homeService.write(homeDto);
		
		/*
		 * String test = req.getParameter("test"); // text mapping
		 * MultipartFile mf = req.getFile("file"); // file mapping String uploadPath =
		 * "";
		 * 
		 * String path = "C:\\"+"fileUpload\\"; // file 업로드 경로
		 * 
		 * String original = mf.getOriginalFilename(); // 업로드하는 파일 name
		 * 
		 * System.out.println("*****"+test+"*****");
		 * System.out.println("*****"+original+"*****");
		 * System.out.println("*****"+mf.getSize()+"*****");
		 * 
		 * uploadPath = path+original;
		 * 
		 * try { mf.transferTo(new File(uploadPath)); // 파일을 위에 지정 경로로 업로드 } catch (
		 * Exception e ) {}
		 * 
		 * homeService.write(homeDto);
		 */
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
