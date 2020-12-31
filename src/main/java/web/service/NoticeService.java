package web.service;

import java.util.List;

import web.dao.NoticeDao;
import web.model.Notice;

public class NoticeService {

	
	NoticeDao noticedao = new NoticeDao();
	
	public List<Notice> ServiceList(int page) {
			
		return noticedao.selectlist(page);
	}
}
