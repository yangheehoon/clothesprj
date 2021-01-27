package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import web.model.Member;

public class MemberDao {

	public boolean idcheck(String id , String pw) {
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
					return false;
				}
			}else {
				System.out.println("존재하지 않는 아이디입니다.");
				return false;
			}
			
			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
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
	
	public void InsertMember(String id, String pw, String nickname, 
			String name, int birth, String email, String gender, 
			String phone_num) {
		
		String sql = "insert into value('"+id+"','"+pw+"','"
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean idcheck2(String id) {
		String sql = "select * from member where id ='"
				+ id+"'";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if(rs.next()) {
				System.out.println("사용불가능한 아이디입니다.");
				return false;
			}else {
				System.out.println("사용가능한 아이디입니다.");
			}
			
			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
