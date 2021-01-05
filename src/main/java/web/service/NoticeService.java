package web.service;

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
}
