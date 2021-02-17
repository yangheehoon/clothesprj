package web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.model.Board;
import web.model.Comment;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlsession;
	
	public List<Board> SelectList(int page, String field, String query) {
		Map<String, Object> param_map = new HashMap<>(); 
		param_map.put("page", page); 
		param_map.put("field", field);
		param_map.put("query", query);

		return sqlsession.selectList("mapper_board.SelectList", param_map);			
	}
	
	public int SelectCount(String field , String query) {
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("field", field);
		param_map.put("query", query);
		
		return sqlsession.selectOne("mapper_board.SelectCount", param_map);
	}
	
	public Board SelectDetail(int num) {
		
		return sqlsession.selectOne("mapper_board.SelectDetail", num);
	}
	
	public Board SelectPrevD(int num) {
		
		return sqlsession.selectOne("mapper_board.SelectPrevD", num);
	}
	
    public Board SelectNextD(int num) {
		
    	return sqlsession.selectOne("mapper_board.SelectNextD", num);
	}
    
    public List<Comment> SelectDetailCmt(int board_num){
    	       // 서비스에서 받은 num값 변수명 board_num로변경	    	    
       
    	return sqlsession.selectList("mapper_board.SelectDetailCmt", board_num);    		
    }
   
	public void InsertCmt(String cmt_content, String writer_id, 
			int board_num) {
			// 서비스에서 받은 num값 변수명 board_num로변경
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("cmt_content", cmt_content);
		param_map.put("writer_id", writer_id);
		param_map.put("board_num", board_num);
		
		sqlsession.insert("mapper_board.InsertCmt", param_map);
	}

	public List<Comment> SelectReCmt() {
		
		return sqlsession.selectList("mapper_board.SelectReCmt");
	}
	
	public void InsertReCmt(String writer_id , String recontent,int cmt_num) {
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("writer_id", writer_id);
		param_map.put("recontent", recontent);
		param_map.put("cmt_num", cmt_num);
		
		sqlsession.insert("mapper_board.InsertReCmt", param_map);
	}
	
	public void UpdateHit(int num) {
		
		sqlsession.update("mapper_board.UpdateHit",num);
	}
	
	public void InsertBoard(String title,String writer_id,String content,String files) {
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("title", title);
		param_map.put("writer_id", writer_id);
		param_map.put("content", content);
		param_map.put("files", files);
		
		sqlsession.insert("mapper_board.InsertBoard", param_map);
	}
	
	public void DelBoard(int delnum) {
		
		sqlsession.delete("mapper_board.DelBoard",delnum);
	}
	
	public void DelCmt(int cmtnum) {
		
		sqlsession.delete("mapper_board.DelCmt",cmtnum);
	}
	public void DelReCmt(int recmtnum) {
		
		sqlsession.delete("mapper_board.DelReCmt",recmtnum);
	}
}