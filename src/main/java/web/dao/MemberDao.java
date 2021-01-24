package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import web.model.Member;

public class MemberDao {

	public Member idcheck(String id , String pw) {
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
				}
			}else {
				System.out.println("존재하지 않는 아이디입니다.");
			}
			
			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Member logincheck(String id , String pw) {
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
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String phon_num = rs.getString("phon_num");
				
				member = new Member(id, pw, nickname, name, email, gender, phon_num);
			
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
	public Member ckcheck(String ck) {
		String sql = "select * from member where id ='"
				+ ck+"'";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		Member member = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String nickname = rs.getString("nickname");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				String phon_num = rs.getString("phon_num");
				
				member = new Member(id, pw, nickname, name, email, gender, phon_num);
			
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
}
