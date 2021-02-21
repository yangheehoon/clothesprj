package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.model.Member;

@Repository
public class MemberDao {

	@Autowired
	SqlSession sqlsession;
	
	public String IdCheck(String id , String pw) {

	String rs = sqlsession.selectOne("mapper_member.IdCheck", id);
		
			if(rs!=null) {
				if(pw.equals(rs)) {
					System.out.println("로그인 성공.");
				}else {
					System.out.println("비밀번호가 틀렸습니다.");
					return "pwfail";
				}
			}else {
				System.out.println("존재하지 않는 아이디입니다.");
				return "idnone";
			}
					
		return "success";
	}
	
	public Member SelectMember(String id , String pw) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("id", id);
		param_map.put("pw", pw);
		
		return sqlsession.selectOne("mapper_member.SelectMember", param_map);
				
	}
	
	public void InsertMember(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("id", id);
		param_map.put("pw", pw);
		param_map.put("nickname", nickname);
		param_map.put("name", name);
		param_map.put("birth", birth);
		param_map.put("email", email);
		param_map.put("gender", gender);
		param_map.put("phone_num", phone_num);
		
		sqlsession.insert("mapper_member.InsertMember", param_map);
		
	}
	
	public void DelMember(String id, String pw) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("id", id);
		param_map.put("pw", pw);
		
		sqlsession.delete("mapper_member.DelMember", param_map);
		
	}
	
	public void UpdatePw(String pw, String id) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("id", id);
		param_map.put("pw", pw);
		
		sqlsession.update("mapper_member.UpdatePw", param_map);
	}
	
	public void UpdateMember(String nickname, String email, String phone_num, String id) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("nickname", nickname);
		param_map.put("email", email);
		param_map.put("phone_num", phone_num);
		param_map.put("id", id);
		
		sqlsession.update("mapper_member.UpdateMember", param_map);
		
	}

	public void UpdateMember2(String nickname, String email, String phone_num, String pro_file, String id) {

		Map<String,Object> param_map = new HashMap<>();
		param_map.put("nickname", nickname);
		param_map.put("email", email);
		param_map.put("phone_num", phone_num);
		param_map.put("pro_file", pro_file);
		param_map.put("id", id);
		
		sqlsession.update("mapper_member.UpdateMember2", param_map);
		
	}
		
}
