package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web.model.Notice;

public class NoticeDao {
	
	Notice notice = new Notice();
	
	
	public ArrayList<Notice> insertlist() {
		
		return null;
	}
	public ArrayList<Notice> dellist() {
		
		return null;
	}
	public List<Notice> selectlist() {
		
		List<Notice> list = new ArrayList<Notice>();
		
		String sql = "select * from notice";
		
		 String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 Connection con = DriverManager.getConnection(url,"c##clothes", "1234");
				 PreparedStatement st = con.prepareStatement(sql);
				 //st.setString(1, "%"+query+"%");
				 //st.setInt(2, 1+(page-1)*10);
				 //st.setInt(3, page*10);
				 ResultSet rs = st.executeQuery();

				 while(rs.next()) {
				 
				 int num = rs.getInt("num");
				 String title = rs.getString("title");
				 Date regdate = rs.getDate("regdate"); 
				 String writer_id = rs.getString("writer_id"); 
				 String hit = rs.getString("hit"); 
		
					notice.setNum(num);
					notice.setTitle(title);
					notice.setRegdate(regdate);
					notice.setWriter_id(writer_id);
					notice.setHit(hit);
				 
				 list.add(notice);
				 }
				 rs.close();
				 st.close();
				 con.close();
			 } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 System.out.println(notice.getNum());
		 System.out.println(notice.getWriter_id());
		 System.out.println(notice.toString());
		 
		return list;
		
		
	}
}