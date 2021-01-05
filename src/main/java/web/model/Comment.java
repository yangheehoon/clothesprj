package web.model;

import java.util.Date;

public class Comment {
	int num;
	String writer_id;
	String content;
	Date regdate;
	int notice_num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(int num, String writer_id, String content,Date regdate,int notice_num ) {
		this.num = num;
		this.writer_id = writer_id;
		this.content = content;
		this.regdate = regdate;
		this.notice_num = notice_num;
	}
	@Override
	public String toString() {
		return "Comment [num=" + num + ", writer_id=" + writer_id + ", content=" + content + ", regdate=" + regdate
				+ ", notice_num=" + notice_num + "]";
	}
	
}
