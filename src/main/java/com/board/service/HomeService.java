package com.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dao.HomeDao;
import com.board.dto.FileDto;
import com.board.dto.HomeDto;

@Service
public class HomeService {
	
	@Autowired
	private HomeDao homeDao;
	
	public List<HomeDto> list() throws Exception {
		return homeDao.list();
	}
	
	public void write(FileDto fileDto, MultipartHttpServletRequest mreq) throws Exception {
		homeDao.write(fileDto);
		
		List<Map<String, Object>> list = parseInsertFileInfo(fileDto, mreq);
		int size = list.size();
		for ( int i = 0; i < size; i++ ) {
			homeDao.writeFile(list.get(i));
		}
	}
	
	public List<Map<String, Object>> parseInsertFileInfo(FileDto fileDto, MultipartHttpServletRequest mreq) throws Exception {
		
		String filePath = "C:\\fileUpload\\";
	
		Iterator<String> iterator = mreq.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		int bno = fileDto.getBno();
		
		File file = new File(filePath);
		if ( file.exists() == false ) {
			file.mkdirs();
		}
		
		while ( iterator.hasNext() ) {
			multipartFile = mreq.getFile(iterator.next());
			if ( multipartFile != null ) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("bno", bno);
				listMap.put("org_fname", originalFileName);
				listMap.put("save_fname", storedFileName);
				listMap.put("fpath", filePath+storedFileName);
				list.add(listMap);
			}
		}
		
		return list;
			
		}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public FileDto detail(int bno) throws Exception {
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
	
	public FileDto fileDownload(FileDto data) throws Exception {
		return homeDao.fileDownload(data);
	}
	
}
