package web.model;

import java.util.Date;

public class Notice {

	private int num;
	private String title;
	private String writer_id;
	private String files;
	private String hit;
	private Date regdate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Notice [num=" + num + ", title=" + title + ", writer_id=" + writer_id + ", files=" + files + ", hit="
				+ hit + ", regdate=" + regdate + "]";
	}
	
	
	
}
