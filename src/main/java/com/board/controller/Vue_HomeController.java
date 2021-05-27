package com.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.dto.FileDto;
import com.board.service.Vue_HomeService;

@Controller
public class Vue_HomeController {
	
	@Inject
	private Vue_HomeService vue_homeService;
	
	private static final Logger logger = LoggerFactory.getLogger(Vue_HomeController.class);
	
	@RequestMapping(value = "/vue_test", method = RequestMethod.GET)
	public String getVuetest() throws Exception {
		return "default";
	}
	
	@RequestMapping(value = "/vue_home", method = RequestMethod.POST)
	public @ResponseBody FileDto list() throws Exception {
		FileDto vm = new FileDto();
		vm.setFileDtoList(vue_homeService.list());

		return vm;
	}
	
	@RequestMapping(value = "/vue_detail", method = RequestMethod.POST)
	public @ResponseBody FileDto detail(@RequestBody FileDto vm) throws Exception {
		vm = vue_homeService.detail(vm.getBno());
		
		return vm;
	}
	
	@RequestMapping(value = "/vue_update", method = RequestMethod.POST)
	public FileDto update(@RequestBody FileDto vm) throws Exception {
		vm = vue_homeService.detail(vm.getBno());
		
		return vm;
	}

}
