package web.model;

import java.util.Date;

public class Board extends Notice{
	private int cmt_count;
	
	
	public int getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}

	public Board() {
		
	}
	
	public Board(int num, String title, String writer_id, 
			String content, String files, String hit, 
			Date regdate, int cmt_count) {
		super(num, title, writer_id, files, hit, regdate, content);
		this.cmt_count = cmt_count;
	}
}
