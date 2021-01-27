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
	
	public void ServiceJoin(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		memberdao.InsertMember(id, pw, nickname, name, birth, email, gender, phone_num);
	}

	public String Serviceidcheck(String id) {
		if(memberdao.idcheck2(id)==true) {
			return "1";
		}else {
			return "0";
		}				
	}
}
