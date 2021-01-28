package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import web.model.Member;

public class MemberDao {

	public String IdCheck(String id , String pw) {
		String sql = "select * from member where id ='"
				+ id+"'";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				if(pw.equals(rs.getString("pw"))) {
					System.out.println("로그인 성공.");
				}else {
					System.out.println("비밀번호가 틀렸습니다.");
					return "pwfail";
				}
			}else {
				System.out.println("존재하지 않는 아이디입니다.");
				return "idnone";
			}
			
			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
	public Member SelectMember(String id , String pw) {
		String sql = "select * from member where id ='"
				+ id+"' and pw ='"+pw+"'";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		Member member = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				id = rs.getString("id");
				pw = rs.getString("pw");
				String nickname = rs.getString("nickname");
				String name = rs.getString("name");
				int birth = rs.getInt("birth");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String phone_num = rs.getString("phone_num");
				Date regdate = rs.getDate("regdate");
				
				member = new Member(id, pw, nickname, name, birth, email, gender, phone_num, regdate);
			
			}	
			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public String InsertMember(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		String sql = "insert into member values('"+id+"','"+pw+"','"
				+nickname+"','"+name+"','"+birth+"','"+email
				+"','"+gender+"','"+phone_num+"',sysdate)";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			
			con.close();
			st.close();
			rs.close();
			
			return "redirect:/member/success";
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "redirect:/member/fail";
		} catch (SQLException e) {
			e.printStackTrace();
			return "redirect:/member/fail";
		}
		
	}
	public void DelMember(String id, String pw) {
		
		String sql ="delete from member where id='"+id
				+"' and pw='"+pw+"'"; 
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			
			con.close();
			st.close();
			rs.close();
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();			
		}		
		
	}
	public void UpdatePw(String pw, String id) {
		
		String sql = "update member set pw ='"+pw 
				+"' where id='"+id+"'"; 
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			
			con.close();
			st.close();
			rs.close();
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();			
		}		
		
	}
	
	
}
