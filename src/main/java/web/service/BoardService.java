package web.service;

import java.util.List;

import web.dao.BoardDao;
import web.model.Board;
import web.model.Comment;

public class BoardService {

	
	BoardDao boarddao = new BoardDao();
	
	public List<Board> ServiceList(int page, String filed ,String query) {
			
		return boarddao.SelectList(page,filed,query);
	}
	public int ServiceListCount(String filed , String query) {
		
		return boarddao.SelectCount(filed , query);
	}
	public Board ServiceDetail(int num) {
		// TODO Auto-generated method stub
		return boarddao.SelectDetail(num);
	}
	public Board ServicePrevD(int num) {
		return boarddao.SelectPrevD(num);
	}
	public Board ServicNextD(int num) {
		return boarddao.SelectNextD(num);
	}
	public List<Comment> ServiceDetailCmt(int num) {
		return boarddao.SelectDetailCmt(num);
	}
	public void ServiceInsertCmt(String cmt_content, 
			String writer_id, int num) {
		boarddao.InsertCmt(cmt_content,  writer_id, num);
		boarddao.UpdateCmtCount(num);
	}
	public List<Comment> ServiceReCmt(){
		return boarddao.SelectReCmt();
	}
	public void ServiceInsertReCmt(String writer_id, String recontent, int cmt_num) {
		boarddao.InsertReCmt(writer_id, recontent, cmt_num);
	}
	public void ServiceUpdateHit(int num) {
		boarddao.UpdateHit(num);
	}

	public void ServiceInsertBoard(String title,String writer_id, String content, String files) {
		boarddao.InsertBoard(title, writer_id, content, files);
	}
	public void ServiceDelBoard(int delnum) {
		boarddao.DelBoard(delnum);
	}
}
