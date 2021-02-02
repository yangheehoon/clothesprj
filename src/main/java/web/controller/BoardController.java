package web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.Board;
import web.model.Comment;
import web.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	
	BoardService boardservice = new BoardService();
	
	@RequestMapping("/board_list")
	public String BoardList(@RequestParam(value="p" , defaultValue="1") int page, 
			@RequestParam(value="f" , defaultValue="title") String filed,
			@RequestParam(value="q" , defaultValue="") String query,
			Model model) {
		
		    List<Board> list = boardservice.ServiceList(page,filed,query);
			int count = boardservice.ServiceListCount(filed ,query);
			
			model.addAttribute("list", list);
			model.addAttribute("count", count);
		
		return "board/board_list";
	}
	
	@RequestMapping("/board_detail")
	public String BoardDetail(@RequestParam("num") int num,
			@RequestParam(value="cmt_content" , defaultValue= "") String cmt_content,	
			@RequestParam(value="recontent" , defaultValue= "") String recontent, 
			@RequestParam(value="cmt_num", required = false ) Integer cmt_num,
			@RequestParam(value="writer_id", required = false ) String writer_id,
			Model model) {			
		
		if(cmt_content!=""&&writer_id!=null) {  //isEmpty()="" /NULL문자가 아닌 비어있는 값 반환 
			boardservice.ServiceInsertCmt(cmt_content, writer_id, num);
			System.out.println("boardInsertCmt");						
		}
		if(!recontent.isEmpty()&&writer_id!=null) {
			boardservice.ServiceInsertReCmt(writer_id,recontent, cmt_num);
			System.out.println("boardInsertReCmt");			
		}

		boardservice.ServiceUpdateHit(num);
		Board detail = boardservice.ServiceDetail(num);
		Board prevdetail = boardservice.ServicePrevD(num);
		Board nextdetail = boardservice.ServicNextD(num);
		
		List<Comment> cmtlist = boardservice.ServiceDetailCmt(num);
		List<Comment> recmtlist = boardservice.ServiceReCmt();
		
			model.addAttribute("detail", detail);
			model.addAttribute("prevdetail", prevdetail);
			model.addAttribute("nextdetail", nextdetail);
			model.addAttribute("cmtlist", cmtlist);
			model.addAttribute("recmtlist", recmtlist);
		
		return "board/board_detail";
	}
	
	@RequestMapping("/board_add")
	public String AddBoard(HttpSession session) {
		if(session.getAttribute("member")==null) {
			return "redirect:/member/login";
		}
			
		return "board/board_add";
	}
	@RequestMapping("/board_add2")
	public String AddBoard2(@RequestParam(value="title" , required=false) String title,
			@RequestParam(value="content" , required=false) String content,			
			@RequestParam(value="files" , defaultValue="") String files,			
			@RequestParam(value="writer_id" , required=false) String writer_id,			
			Model model) {
			
		if(title!=null && content!=null && writer_id!=null) {
			boardservice.ServiceInsertBoard(title, writer_id, content, files);
			System.out.println("insertboard");
		}
		
		return "redirect:/board/board_list";
	}

	@RequestMapping("/board_del")   //서브밋
	public String DelBoard(@RequestParam("delnum") int delnum) {
		
			boardservice.ServiceDelBoard(delnum);
			System.out.println("delboard");
		
		return "redirect:/board/board_list";
	}
	
	@RequestMapping("/board_cmt_del")   //ajax
	public String DelCmt(@RequestParam("cmtnum") int cmtnum) {
		
			boardservice.ServiceDelCmt(cmtnum);
			System.out.println("delboardcmt");
		
		return "board/board_list";
	}
	
	@RequestMapping("/board_recmt_del")   //ajax
	public String DelReCmt(@RequestParam("recmtnum") int recmtnum) {
		
			boardservice.ServiceDelReCmt(recmtnum);
			System.out.println("delboardrecmt");
		
		return "board/board_list";
	}
}

