package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dto.HomeDto;
import com.board.service.HomeService;

@Controller
public class HomeController {
	
	@Inject
	private HomeService homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getList(Model model) throws Exception {
		List<HomeDto> list = null;
		list = homeService.list();
		
		try {
			model.addAttribute("list", list);
		} catch ( Exception e ) {}
		
		return "home";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite() throws Exception {
		
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(HomeDto homeDto) throws Exception {
		homeService.write(homeDto);
		
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
		
		return "/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(HomeDto homeDto) throws Exception {
		homeService.update(homeDto);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String getDelete(Model model, int bno) throws Exception {
		homeService.delete(bno);
		
		return "redirect:/";
	}
	
	
}
