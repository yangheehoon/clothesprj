package web.model;

import java.util.Date;

public class ReComment {
	int num;
	String writer_id;
	String content;
	Date regdate;
	int cmt_num;
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
	public int getCmt_num() {
		return cmt_num;
	}
	public void setCmt_num(int cmt_num) {
		this.cmt_num = cmt_num;
	}
	
	public ReComment() {
		// TODO Auto-generated constructor stub
	}
	public ReComment(int num, String writer_id, String content,Date regdate,int cmt_num ) {
		this.num = num;
		this.writer_id = writer_id;
		this.content = content;
		this.regdate = regdate;
		this.cmt_num = cmt_num;
	}
	@Override
	public String toString() {
		return "Comment [num=" + num + ", writer_id=" + writer_id + ", content=" + content + ", regdate=" + regdate
				+ ", cmt_num=" + cmt_num + "]";
	}
	
}
