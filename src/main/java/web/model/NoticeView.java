package web.model;

import java.util.Date;

public class NoticeView extends Notice{
	private int cmt_count;
	private int num;
	private String title;
	private String writer_id;
	private String files;
	private String hit;
	private Date regdate;
	//private String content; 

	
	public int getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}
	
	public NoticeView() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeView(int num, String title, String writer_id, 
			String files,String hit, Date regdate,int cmt_count) {
		// TODO Auto-generated constructor stub
		super(num,title,writer_id,files,hit,regdate,"");
		this.cmt_count=cmt_count;
	}
}
