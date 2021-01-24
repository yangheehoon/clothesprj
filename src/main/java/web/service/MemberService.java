package web.service;

import web.dao.MemberDao;
import web.model.Member;

public class MemberService {

	MemberDao memberdao = new MemberDao();
	
	public Member idcheck(String id , String pw) {
			memberdao.idcheck(id , pw);
		return memberdao.logincheck(id, pw);
	}
	public Member ckcheck(String ck) {
		
	return memberdao.ckcheck(ck);
	}
}
