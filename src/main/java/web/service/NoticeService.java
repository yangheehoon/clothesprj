package web.service;

import java.util.Date;
import java.util.List;

import web.dao.NoticeDao;
import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;

public class NoticeService {

	
	NoticeDao noticedao = new NoticeDao();
	
	public List<NoticeView> ServiceList(int page, String filed ,String query) {
			
		return noticedao.SelectList(page,filed,query);
	}
	public int ServiceListCount(String filed , String query) {
		
		return noticedao.SelectCount(filed , query);
	}
	public Notice ServiceDetail(int num) {
		// TODO Auto-generated method stub
		return noticedao.SelectDetail(num);
	}
	public Notice ServicePrevD(int num) {
		return noticedao.SelectPrevD(num);
	}
	public Notice ServicNextD(int num) {
		return noticedao.SelectNextD(num);
	}
	public List<Comment> ServiceDetailCmt(int num) {
		return noticedao.SelectDetailCmt(num);
	}
	public void ServiceInsertCmt(String content, 
			String writer_id, int num) {
		noticedao.InsertCmt(content,  writer_id, num);
	}
	public List<Comment> ServiceReCmt(){
		return noticedao.SelectReCmt();
	}
	public void ServiceInsertReCmt(String writer_id, String recontent, int cmt_num) {
		noticedao.InsertReCmt(writer_id, recontent, cmt_num);
	}
	public void ServiceUpdateHit(int num) {
		noticedao.UpdateHit(num);
	}
	public int ServiceCmtCount(int num) {
		return noticedao.SelectCmtCount(num);
	}
	public void ServiceInsertNotice(String title,String writer_id, String content, String files) {
		noticedao.InsertNotice(title, writer_id, content, files);
	}
}
