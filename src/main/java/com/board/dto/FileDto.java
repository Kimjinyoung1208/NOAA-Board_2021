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
