package com.board.dto;

public class FileDto {
	
	private int fno;
	private int bno;
	private String org_fname;
	private String save_fname;
	private String fpath;
	private String title;
	private String contents;
	private String writer;
	private boolean result;
	private String sortOption;
	private int num;
	private String searchOption;
	
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean isResult() {
		return result;
	}
	public String getSortOption() {
		return sortOption;
	}
	public void setSortOption(String sortOption) {
		this.sortOption = sortOption;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getOrg_fname() {
		return org_fname;
	}
	public void setOrg_fname(String org_fname) {
		this.org_fname = org_fname;
	}
	public String getSave_fname() {
		return save_fname;
	}
	public void setSave_fname(String save_fname) {
		this.save_fname = save_fname;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

}
