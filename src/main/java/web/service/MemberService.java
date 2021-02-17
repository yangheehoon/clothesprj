package web.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.MemberDao;
import web.model.Member;

@Service
public class MemberService {

	@Autowired
	MemberDao memberdao;
	
	public String ServiceIdCheck(String id , String pw , HttpSession session) {

		String result = memberdao.IdCheck(id , pw);
		
		if(result == "idnone") {
			return "idnone";
		}else if(result == "pwfail"){
			return "pwfail";
		}else {	 //로그인		
			session.removeAttribute("cart");
			Member member = memberdao.SelectMember(id, pw);			
			session.setAttribute("member", member);
			return "success";
		}				
	}
	
	
	public void ServiceJoin(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		memberdao.InsertMember(id, pw, nickname, name, birth, email, gender, phone_num);
	}


	public String ServicePwCheck(String id, String pw) {
			
		return memberdao.IdCheck(id, pw);
	}


	public void ServiceDelMember(String id , String pw) {
		memberdao.DelMember(id ,pw);
	}


	public void ServiceChangePw(String pw, String id) {
		memberdao.UpdatePw(pw,id);
		
	}


	public void ServiceChangeMember(String nickname, String email, String phone_num, String id, String pw, HttpSession session) {
		memberdao.UpdateMember(nickname,email,phone_num,id);
		Member member = memberdao.SelectMember(id,pw);
		session.setAttribute("member", member);
	}

	
}
