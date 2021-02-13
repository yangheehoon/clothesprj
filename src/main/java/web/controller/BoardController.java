package web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.model.Board;
import web.model.Comment;
import web.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private ServletContext ctx;
	
	@Autowired
	BoardService boardservice;
	
	@RequestMapping("/board_list")
	public String BoardList(@RequestParam(value="p" , defaultValue="1") int page, 
			@RequestParam(value="f" , defaultValue="title") String field,
			@RequestParam(value="q" , defaultValue="") String query,
			Model model) {
		
		    List<Board> list = boardservice.ServiceList(page,field,query);
			int count = boardservice.ServiceListCount(field ,query);
			
			model.addAttribute("list", list);
			model.addAttribute("count", count);
		
		return "board/board_list";
	}
	
	@RequestMapping("/board_detail")
	public String BoardDetail(@RequestParam("num") int num,
			@RequestParam(value="cmt_content" , defaultValue="") String cmt_content,	
			@RequestParam(value="recontent" , defaultValue="") String recontent, 
			@RequestParam(value="cmt_num", required = false ) Integer cmt_num,
			@RequestParam(value="writer_id", required = false ) String writer_id,
			Model model) {			
		
		if(!cmt_content.isEmpty()&&writer_id!=null) {  
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
			@RequestParam(value="files") MultipartFile[] reqfiles,			
			@RequestParam(value="writer_id" , defaultValue="") String writer_id,			
			Model model) throws IllegalStateException, IOException {
			
        StringBuilder builder = new StringBuilder();
		
        for(MultipartFile reqfile : reqfiles) {
			if(reqfile.getSize()==0) {
				continue;
			}
			String filename = reqfile.getOriginalFilename();
			builder.append(filename+",");
			String realpath= ctx.getRealPath("/resources/board");
			System.out.println(realpath);
			realpath += File.separator + filename;
			File savefile = new File(realpath);
			reqfile.transferTo(savefile);
		}
			if(!builder.toString().equals("")) {
				builder.delete(builder.length()-1, builder.length());
			}
			String files = builder.toString();
		
		if(title!=null && content!=null && !writer_id.equals("")) {
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

