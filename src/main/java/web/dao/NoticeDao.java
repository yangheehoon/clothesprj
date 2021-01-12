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
    			+ " notice_num = " + notice_num
    			+ " order by regdate asc ";
    	
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
   
	public void InsertCmt(String content, String writer_id, 
			int notice_num) {
			// 서비스에서 받은 num값 변수명 notice_num로변경
		
		String sql = " insert into \"comment\" values "
				+ " ( seqnext.NEXTVAL,'" + content + "' ,sysdate, '" 
				+ writer_id + "'," +  notice_num + " ) ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		}

	public List<Comment> SelectReCmt() {
		
		List<Comment> recmtlist = new ArrayList<Comment>();
		
		String sql="select * from recomment";
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				int num =rs.getInt("num");
				String writer_id= rs.getString("writer_id");
				String content=rs.getString("content");
				Date regdate=rs.getDate("regdate");
				int notice_num=rs.getInt("cmt_num");
				// 커맨트 객체를 재사용할거기때문에
				// cmt_num이름을 notice_num으로 사용
				
				Comment recmt = new Comment(num, writer_id, content, regdate, notice_num); 
			    recmtlist.add(recmt);
			}
			
			System.out.println(recmtlist);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		
		return recmtlist;
	}
	public void InsertReCmt(String writer_id , String recontent,int cmt_num) {
		String sql = " insert into recomment values( "
				+ " recmtnum.NEXTVAL , '" + writer_id + "','" 
				+ recontent + "',sysdate ," + cmt_num + " ) "; 
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
	public void UpdateHit(int num) {
		String sql=" update notice set hit=hit+1 where "
				+ " num = " + num;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public int SelectCmtCount(int num) {
		String sql=" select count(c.num) cmt_count from notice "
				+ " N left OUTER join \"comment\" C on " 
				+ " N.num=C.notice_num where n.num= "+ num; 
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		int cmt_count=0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				cmt_count=rs.getInt("cmt_count");
			}
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cmt_count;
	}
	
	public void InsertNotice(String title,String writer_id,String content,String files) {
		String sql="insert into notice values"
				+ "(seqnoticenum.NEXTVAL,'"+title+"','"
				+writer_id+"','"+content+"',sysdate,0,'"
				+files+"') ";
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DelNotice(int delnum) {
		String sql="delete from notice where num = "+delnum;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}