package web.model;

import java.util.Date;

public class Board_ReComment {
	int num;
	String writer_id;
	String content;
	Date regdate;
	int board_comment_num;
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
	public int getBoard_comment_num() {
		return board_comment_num;
	}
	public void setBoard_comment_num(int board_comment_num) {
		this.board_comment_num = board_comment_num;
	}
	
	public Board_ReComment() {
		// TODO Auto-generated constructor stub
	}
	public Board_ReComment(int num, String writer_id, String content,Date regdate,int board_comment_num ) {
		this.num = num;
		this.writer_id = writer_id;
		this.content = content;
		this.regdate = regdate;
		this.board_comment_num = board_comment_num;
	}
	@Override
	public String toString() {
		return "Comment [num=" + num + ", writer_id=" + writer_id + ", content=" + content + ", regdate=" + regdate
				+ ", board_comment_num=" + board_comment_num + "]";
	}
	
}
