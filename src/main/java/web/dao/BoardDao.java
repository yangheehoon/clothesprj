package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web.model.Board;
import web.model.Comment;

public class BoardDao {
	

	public List<Board> SelectList(int page, String filed, String query) {
		
		List<Board> list = new ArrayList<Board>();
		
		String sql = 
				" select * from " +
				" (select ROWNUM RNUM, N.* FROM " +
				" (select * from boardview where " + filed + 
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
				 String content = null; 
				 int cmt_count = rs.getInt("cmt_count"); 
		
				 Board board = new Board(num, title, writer_id, content, files, hit, regdate, cmt_count);
				 
				 list.add(board);
				 }
				 rs.close();
				 st.close();
				 con.close();
			 } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 
		return list;		
		
	}
	public int SelectCount(String filed , String query) {
		int count = 0;
		
		String sql = " select count(num) count from "
				+ " (select * from board where " + filed 
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
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	public Board SelectDetail(int num) {
		
		Board detail = null;
		
		String sql = "select * from board where num = " 
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
				int cmt_count = 0;
				
				detail = new Board(num, title, writer_id, content, files, hit, regdate, cmt_count);
				
			}
			
			rs.close();
			st.close();
			con.close();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detail;
	}
	
	public Board SelectPrevD(int num) {
		
		Board notice =null;
		
		String sql=" select * from (select * from board where "
				+ " regdate < (select regdate from board where "
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
			int cmt_count = 0;
			
			notice = new Board(num, title, writer_id, content, files, hit, regdate, cmt_count);
		}
		
		
		rs.close();
		st.close();
		con.close();
		
		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	
    public Board SelectNextD(int num) {
		
    	Board notice = null;
    	
    	String sql= " select * from"
    			+ " (select * from board where regdate > " 
    			+ " (select regdate from board where num = "
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
    			int cmt_count = 0;
    			
    			notice = new Board(num, title, writer_id, content, files, hit, regdate, cmt_count);
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
    
    public int SelectCmtCount(int num) {
		String sql="select cmt_count from boardview bv where bv.num= "+ num; 
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
    
    public List<Comment> SelectDetailCmt(int board_num){
    	       // 서비스에서 받은 num값 변수명 board_num로변경
	    List<Comment> cmtlist = new ArrayList<Comment>();
    	
    	String sql= " select * from board_comment where "
    			+ " board_num = " + board_num
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
    		    
    		    int notice_num = board_num;
    		
    		    Comment cmt = new Comment(num, writer_id, content, regdate, notice_num);
    		    cmtlist.add(cmt);
    		}
    		
    		rs.close();
    		st.close();
    		con.close();
    		
    	}catch (ClassNotFoundException e) {
    		e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	return cmtlist;
    }
   
	public void InsertCmt(String cmt_content, String writer_id, 
			int board_num) {
			// 서비스에서 받은 num값 변수명 board_num로변경
		
		String sql = " insert into board_comment values "
				+ " ( board_comment_num.NEXTVAL,'" + cmt_content + "' ,sysdate, '" 
				+ writer_id + "'," +  board_num + " ) ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xe";		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
			st.close();
			con.close();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		}

	public List<Comment> SelectReCmt() {
		
		List<Comment> recmtlist = new ArrayList<Comment>();
		
		String sql="select * from board_recomment order by regdate asc";
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
				int notice_num=rs.getInt("board_comment_num");
				// 커맨트 객체를 재사용할거기때문에
				// cmt_num이름을 notice_num으로 사용
				
				Comment recmt = new Comment(num, writer_id, content, regdate, notice_num); 
			    recmtlist.add(recmt);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		
		return recmtlist;
	}
	public void InsertReCmt(String writer_id , String recontent,int cmt_num) {
		String sql = " insert into board_recomment values( "
				+ " board_recomment_num.NEXTVAL , '" + writer_id + "','" 
				+ recontent + "',sysdate ," + cmt_num + " ) "; 
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
            PreparedStatement st = con.prepareStatement(sql);
            st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void UpdateHit(int num) {
		String sql=" update board set hit=hit+1 where "
				+ " num = " + num;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void InsertBoard(String title,String writer_id,String content,String files) {
		String sql="insert into board values"
				+ "(board_num.NEXTVAL,'"+title+"','"
				+writer_id+"','"+content+"',sysdate,0,'"
				+files+"') ";
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DelBoard(int delnum) {
		String sql="delete from board where num = "+delnum;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void DelCmt(int cmtnum) {
		String sql="delete from board_comment where num = "+cmtnum;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void DelReCmt(int recmtnum) {
		String sql="delete from board_recomment where num = "+recmtnum;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.executeQuery();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}