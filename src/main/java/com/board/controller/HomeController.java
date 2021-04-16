package com.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
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
		FileDto fileDto = homeService.fileDownload(bno);
		
		System.out.println(fileDto.getBno());
		System.out.println(fileDto.getOrg_fname());
		System.out.println(fileDto.getSave_fname());
		System.out.println(fileDto.getFpath());
		
		String save_fname = fileDto.getSave_fname();
		String org_fname = fileDto.getOrg_fname();
		String fpath = fileDto.getFpath();
		
		File file = new File(fpath);
		
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		
		try {
			String downName = null;
			String browser = req.getHeader("User-Agent");
			
			if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				downName = URLEncoder.encode(org_fname, "UTF-8").replaceAll("\\+", "%20");
			} else {
				downName = new String(org_fname.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			res.setHeader("Content-Disposition", "attachment;filename=\""+downName+"\"");
			res.setContentType("application/octer-stream");
			res.setHeader("Content-Transfer-Encoding", "binary");
			
			fileInputStream = new FileInputStream(file);
			servletOutputStream = res.getOutputStream();
			
			byte b[] = new byte[1024];
			int data = 0;
			
			while((data = (fileInputStream.read(b, 0, b.length))) != -1) {
				servletOutputStream.write(b, 0, data);
			}
			
			servletOutputStream.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileInputStream != null) {
				try {
	                fileInputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			}
		}
		
	}

}
