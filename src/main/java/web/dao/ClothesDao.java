package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web.model.Clothes;

public class ClothesDao {
	
	public List<Clothes> SelectClothesList(){
		return SelectClothesList("",1);
	}
	
	public List<Clothes> SelectClothesList(String query,int page){
		
		List<Clothes> clotheslist = new ArrayList<Clothes>();
		
		String sql="select * from (select ROWNUM RNUM ,T.* from "
				+ "(select * from clothes where name like '%"+query+"%'"
				+ " order by regdate desc)T) where RNUM between "
				+ (1+(page-1)*5) +" and "+(page*5);
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				String description = rs.getString("description");
				String files = rs.getString("files");
				Date regdate = rs.getDate("regdate");
				
				Clothes clothes = new Clothes(num, name, price, description, files, regdate);
				
				clotheslist.add(clothes);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clotheslist;
	}
	
	public int SelectClothesCount(String query) {
		int count =0;
		String sql="select count(num) count from "
				+ "(select * from clothes where "
				+ "name like '%"+query+"%')";
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return count;
	}
	
	public void InsertClothes(String name,int price,
			String description,String files) {
		String sql="insert into clothes values"
				+ "(seqclothesnum.NEXTVAL,'"+name+"','"+price
				+ "','"+description+"','"+files+"',sysdate)";
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
	
	public Clothes SelectClothesDetail(int num) {
		Clothes clothes =null;
		
		String sql="select * from clothes where num="+num;
		String url="jdbc:oracle:thin:@localhost:1521/xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "c##clothes", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				String name=rs.getString("name");
				int price = rs.getInt("price");
				String description=rs.getString("description");
				String files=rs.getString("files");
				Date regdate = rs.getDate("regdate");
				
				clothes = new Clothes(num, name, price, description, files, regdate);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return clothes;
	}
}
