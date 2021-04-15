package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
		FileDto data = homeService.detail(bno);
		
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
	
	@ResponseBody
	@RequestMapping(value = "/fileDownload", method = RequestMethod.POST)
	public void postFileDownload(int bno, HttpServletRequest req, HttpServletResponse res) throws Exception {
		FileDto data = new FileDto();
		data.setBno(bno);
		data = homeService.fileDownload(data);
		
		System.out.println(bno);
		System.out.println(data.getOrg_fname());
		System.out.println(data.getFpath());
		
		String save_fname = data.getSave_fname();
		String org_fname = data.getOrg_fname();
		String fpath = data.getFpath();
		
		res.setHeader("Content-Disposition", "attachment; filename=\"" + org_fname + "\"");
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setHeader("Content-Type", "application/octet-stream");
		res.setHeader("Pragma", "no-cache;");
		res.setHeader("Expires", "-1;");

		OutputStream os = res.getOutputStream();
		FileInputStream fis = new FileInputStream(fpath);

		int readCount = 0;
		byte[] buffer = new byte[1024];

		while((readCount = fis.read(buffer)) != -1) {
			os.write(buffer, 0, readCount);
		}
		fis.close();
		os.close();

		/*
		 * if ( data.getSave_fname().equals("image") ) {
		 * 
		 * 
		 * res.setContentType(MediaType.MULTIPART_FORM_DATA); } else {
		 * res.setContentType(MediaType.APPLICATION_OCTET_STREAM); }
		 */
		
		/*
		 * byte[] fileByte = FileUtils.readFileToByteArray(new File(fpath));
		 * res.setContentType("application/octet-stream");
		 * res.setContentLength(fileByte.length); res.setHeader("Content-Disposition",
		 * "attachment; fileName=\"" + URLEncoder.encode(org_fname,"UTF-8")+"\";");
		 * res.setHeader("Content-Transfer-Encoding", "binary");
		 * res.getOutputStream().write(fileByte);
		 * 
		 * res.getOutputStream().flush(); res.getOutputStream().close();
		 */
	}

}
