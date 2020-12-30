package web.service;

import org.springframework.beans.factory.annotation.Autowired;

import web.dao.NoticeDao;

public class NoticeService {

	
	NoticeDao noticedao = new NoticeDao();
	
	public String ServiceList() {
			noticedao.selectlist();
		return null;
	}
}
