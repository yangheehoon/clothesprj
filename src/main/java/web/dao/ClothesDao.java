package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.model.Clothes;

@Repository
public class ClothesDao {
	
	@Autowired
	SqlSession sqlsession;
	
	public List<Clothes> SelectClothesList(){
		return SelectClothesList("",1);
	}
	
	public List<Clothes> SelectClothesList(String query,int page){
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("query", query);
		param_map.put("page", page);
		
		return sqlsession.selectList("mapper_clothes.SelectClothesList", param_map);
	}
	
	public int SelectClothesCount(String query) {
	
		return sqlsession.selectOne("mapper_clothes.SelectClothesCount", query);
	}
	
	public void InsertClothes(String name,int price,
			String description,String files) {
		
		Map<String,Object> param_map = new HashMap<>();
		param_map.put("name", name);
		param_map.put("price", price);
		param_map.put("description", description);
		param_map.put("files", files);
				
		sqlsession.insert("mapper_clothes.InsertClothes", param_map);
	}
	
	public Clothes SelectClothesDetail(int num) {
	
		return sqlsession.selectOne("mapper_clothes.SelectClothesDetail", num);
	}

	public void DelClothes(int delnum) {
		
		sqlsession.delete("mapper_clothes.DelClothes", delnum);
	}
}
