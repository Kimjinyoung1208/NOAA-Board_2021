package com.board.dto;

public class FileDto extends HomeDto {
	
	private int fno;
	private int bno;
	private String org_fname;
	private String save_fname;
	private String fpath;
	
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
