package web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.NoticeDao;
import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;

@Service
public class NoticeService {
	
	@Autowired
	NoticeDao noticedao;	
	
	public List<NoticeView> ServiceList(int page, String filed ,String query) {
			
		return noticedao.SelectList(page,filed,query);
	}
	public int ServiceListCount(String filed , String query) throws ClassNotFoundException, SQLException {
		
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
	public void ServiceInsertCmt(String cmt_content, 
			String writer_id, int num) {
		noticedao.InsertCmt(cmt_content,  writer_id, num);
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
	public void ServiceDelNotice(int delnum) {
		noticedao.DelNotice(delnum);
	}
	public void ServiceDelCmt(int cmtnum) {
		noticedao.DelCmt(cmtnum);		
	}
	public void ServiceDelReCmt(int recmtnum) {
		noticedao.DelReCmt(recmtnum);
	}
}
