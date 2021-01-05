package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;

public class NoticeDao {
	
	
	
	
	public ArrayList<Notice> InsertList() {
		
		return null;
	}
	public ArrayList<Notice> DelList() {
		
		return null;
	}
	public List<NoticeView> SelectList(int page) {
		return SelectList(page , "title" , " ");
	}
	public List<NoticeView> SelectList(int page, String filed, String query) {
		
		List<NoticeView> list = new ArrayList<NoticeView>();
		
		String sql = 
				" select * from " +
				" (select ROWNUM RNUM, N.* FROM " +
				" (select * from noticeview where " + filed + 
				" like ? order by regdate desc)N) " +
				" where RNUM BETWEEN ? AND ?";		
		
		 String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		 try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 Connection con = DriverManager.getConnection(url,"c##clothes", "1234");
				 PreparedStatement st = con.prepareStatement(sql);
				 st.setString(1, "%"+query+"%");
				 st.setInt(2, 1+(page-1)*10);
				 st.setInt(3, page*10);
				 ResultSet rs = st.executeQuery();

				 while(rs.next()) {
				
				 
				 int num = rs.getInt("num");
				 String title = rs.getString("title");
				 Date regdate = rs.getDate("regdate"); 
				 String writer_id = rs.getString("writer_id"); 
				 String files = rs.getString("files"); 
				 String hit = rs.getString("hit"); 
				 int cmt_count = rs.getInt("cmt_count"); 
				 //String content = rs.getString("content"); 
		
				 /*Notice notice = new Notice();	 
				 
				 notice.setNum(num);
				 notice.setTitle(title);
				 notice.setRegdate(regdate);
				 notice.setWriter_id(writer_id);
				 notice.setHit(hit);
				 notice.setFiles(files);
				 */
				 NoticeView noticeview = new NoticeView(num, title, writer_id, files, hit, regdate, cmt_count);
				 
				 list.add(noticeview);
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

		// System.out.println(notice.getNum());
		// System.out.println(notice.getWriter_id());
		// System.out.println(notice.toString());
		 System.out.println(list);
		 //System.out.println(list.get(0));
		 
		return list;
		
		
	}
	public int SelectCount(String filed , String query) {
		int count = 0;
		
		String sql = " select count(num) count from "
				+ " (select * from notice where " + filed 
				+ " like ? order by regdate desc) ";

		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query +"%");
			ResultSet rs = st.executeQuery();
			
			
			if(rs.next()) {
				count = rs.getInt("count");
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
		
		System.out.println(count);
		return count;
	}
	public Notice SelectDetail(int num) {
		
		Notice detail = null;
		
		String sql = "select * from notice where num = " 
		             + num;
		             
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
				
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate"); 
				String writer_id = rs.getString("writer_id"); 
				String files = rs.getString("files"); 
				String hit = rs.getString("hit"); 
				String content = rs.getString("content"); 
			
				detail = new Notice(num, title, writer_id, files, hit, regdate, content);
				
			}
			
			rs.close();
			st.close();
			con.close();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(detail);
		
		return detail;
	}
	
	public Notice SelectPrevD(int num) {
		
		Notice notice =null;
		
		String sql=" select * from (select * from notice where "
				+ " regdate < (select regdate from notice where "
				+ " num = " + num + ") "
						+ " order by regdate desc) where ROWNUM =1";
				
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			
			num = rs.getInt("num"); 
			String title = rs.getString("title");
			Date regdate = rs.getDate("regdate"); 
			String writer_id = rs.getString("writer_id"); 
			String files = rs.getString("files"); 
			String hit = rs.getString("hit"); 
			String content = rs.getString("content");
			
			notice = new Notice(num, title, writer_id, files, hit, regdate, content);
		}
		
		
		rs.close();
		st.close();
		con.close();
		
		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		System.out.println(notice);
		
		return notice;
	}
	
    public Notice SelectNextD(int num) {
		
    	Notice notice = null;
    	
    	String sql= " select * from"
    			+ " (select * from notice where regdate > " 
    			+ " (select regdate from notice where num = "
    			+ num + ") order by regdate asc) "
    			+ "where ROWNUM = 1";
    	
    	String url="jdbc:oracle:thin:@localhost:1521/xe";
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
    		PreparedStatement st = con.prepareStatement(sql);
    		ResultSet rs = st.executeQuery();
    		
    		if(rs.next()) {
    			num = rs.getInt("num");
    			String title = rs.getString("title");
    			Date regdate = rs.getDate("regdate"); 
    			String writer_id = rs.getString("writer_id"); 
    			String files = rs.getString("files"); 
    			String hit = rs.getString("hit"); 
    			String content = rs.getString("content");
    			
    			notice = new Notice(num, title, writer_id, files, hit, regdate, content);
    		}
    		
    		rs.close();
    		st.close();
    		con.close();
    		
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return notice;
	}
    
   public List<Comment> SelectDetailCmt(int notice_num){
    	       // 서비스에서 받은 num값 변수명 notice_num로변경
    	List<Comment> cmtlist = new ArrayList<Comment>();
    	
    	String sql= " select * from \"comment\" where "
    			+ " notice_num = " + notice_num;
    	
    	String url= "jdbc:oracle:thin:@localhost:1521/xe";
    	
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
    		PreparedStatement st = con.prepareStatement(sql);
    		ResultSet rs = st.executeQuery();
    		
    		while(rs.next()) {
    			int num = rs.getInt("num");
    		    String writer_id = rs.getString("writer_id");
    		    String content = rs.getString("content");
    		    Date regdate = rs.getDate("regdate");
    		   // notice_num = rs.getInt("notice_num");
    		
    		    Comment cmt = new Comment(num, writer_id, content, regdate, notice_num);
    		    cmtlist.add(cmt);
    		}
    		
    		rs.close();
    		st.close();
    		con.close();
    		
    	//	System.out.println(cmtlist.get(0));
    		System.out.println(cmtlist);
    		
    	}catch (ClassNotFoundException e) {
    		e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	return cmtlist;
    }
	
}