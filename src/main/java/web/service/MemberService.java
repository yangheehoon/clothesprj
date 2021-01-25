package web.service;

import web.dao.MemberDao;
import web.model.Member;

public class MemberService {

	MemberDao memberdao = new MemberDao();
	
	public boolean idcheck(String id , String pw) {
			
		return memberdao.idcheck(id , pw);
	}
	public Member logincheck(String id , String pw) {
		
	return memberdao.logincheck(id, pw);
}
	public Member ckcheck(String ck) {
		
	return memberdao.ckcheck(ck);
	}
}
