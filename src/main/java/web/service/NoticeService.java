package web.service;

import java.util.List;

import web.dao.NoticeDao;
import web.model.Notice;

public class NoticeService {

	
	NoticeDao noticedao = new NoticeDao();
	
	public List<Notice> ServiceList(int page, String filed ,String query) {
			
		return noticedao.selectlist(page,filed,query);
	}
	public int ServiceListCount(String filed , String query) {
		
		return noticedao.selectcount(filed , query);
	}
}
