package web.service;

import javax.servlet.http.HttpSession;

import web.dao.MemberDao;
import web.model.Member;

public class MemberService {

	MemberDao memberdao = new MemberDao();
	
	public String ServiceIdCheck(String id , String pw , HttpSession session) {

		String result = memberdao.IdCheck(id , pw);
		
		if(result == "idnone") {
			return "idnone";
		}else if(result == "pwfail"){
			return "pwfail";
		}else {			
			Member member = memberdao.SelectMember(id, pw);			
			session.setAttribute("member", member);
			return "success";
		}				
	}
	
	
	public String ServiceJoin(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		return memberdao.InsertMember(id, pw, nickname, name, birth, email, gender, phone_num);
	}

	
}
