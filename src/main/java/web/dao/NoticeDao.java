package web.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;

@Repository
public class NoticeDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public List<NoticeView> SelectList(int page) {
		return SelectList(page , "title" , " ");
	}
	
	public List<NoticeView> SelectList(int page, String field, String query) {
		
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("page", page);
		param_map.put("field", field);
		param_map.put("query", query);
		
		return sqlsession.selectList("mapper_notice.SelectList", param_map);
	}
	
	public int SelectCount(String field , String query) throws ClassNotFoundException,SQLException {
		
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("field", field);
		param_map.put("query", query);
		
		return sqlsession.selectOne("mapper_notice.SelectCount", param_map);
	}
	
	public Notice SelectDetail(int num) {
		
		return sqlsession.selectOne("mapper_notice.SelectDetail", num);
	}
	
	public Notice SelectPrevD(int num) {
		
		return sqlsession.selectOne("mapper_notice.SelectPrevD", num);
	}
	
    public Notice SelectNextD(int num) {		
    	
		return sqlsession.selectOne("mapper_notice.SelectNextD", num);
	}
    
   public List<Comment> SelectDetailCmt(int notice_num){
    	       // 서비스에서 받은 num값 변수명 notice_num로변경
    	
    	return sqlsession.selectList("mapper_notice.SelectDetailCmt", notice_num);
    }
   
	public void InsertCmt(String cmt_content, String writer_id, 
			int notice_num) {
			// 서비스에서 받은 num값 변수명 notice_num로변경
		
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("cmt_content", cmt_content);
		param_map.put("writer_id", writer_id);
		param_map.put("notice_num", notice_num);
		
		sqlsession.insert("mapper_notice.InsertCmt", param_map);
	}

	public List<Comment> SelectReCmt() {		
		
		return sqlsession.selectList("mapper_notice.SelectReCmt");
    }
	
	public void InsertReCmt(String writer_id , String recontent,int cmt_num) {

		Map<String, Object> param_map = new HashMap<>();
		param_map.put("writer_id", writer_id);
		param_map.put("recontent", recontent);
		param_map.put("cmt_num", cmt_num);
		
		sqlsession.insert("mapper_notice.InsertReCmt", param_map);
	}
	
	public void UpdateHit(int num) {
		
		sqlsession.update("mapper_notice.UpdateHit", num);
	}
	public int SelectCmtCount(int num) {
		
		return sqlsession.selectOne("mapper_notice.SelectCmtCount", num);
	}
	
	public void InsertNotice(String title,String writer_id,String content,String files) {
		
		Map<String, Object> param_map = new HashMap<>();
		param_map.put("title", title);
		param_map.put("writer_id", writer_id);
		param_map.put("content", content);
		param_map.put("files", files);
		
		sqlsession.insert("mapper_notice.InsertNotice", param_map);
	}
	
	public void DelNotice(int delnum) {
		
		sqlsession.delete("mapper_notice.DelNotice", delnum);
	}

	public void DelCmt(int cmtnum) {
		
		sqlsession.delete("mapper_notice.DelCmt", cmtnum);
	}

	public void DelReCmt(int recmtnum) {
		
		sqlsession.delete("mapper_notice.DelReCmt", recmtnum);
	}
	
}