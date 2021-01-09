package web.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;
import web.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	
	NoticeService noticeservice = new NoticeService();
	
	@RequestMapping("/list")
	public String NoticeList(@RequestParam(value="p" , defaultValue="1") int page, 
			@RequestParam(value="f" , defaultValue="title") String filed,
			@RequestParam(value="q" , defaultValue="") String query, 
			Model model) {
							
		    List<NoticeView> list = noticeservice.ServiceList(page,filed,query);
			int count = noticeservice.ServiceListCount(filed ,query);
			/*List<Notice> commentcount = noticeservice.ServiceListCommentCount();
			*/
			model.addAttribute("list", list);
			model.addAttribute("count", count);
		/*	model.addAttribute("commentcount", commentcount);
		*/	
		return "notice/list";
	}
	
	@RequestMapping("/detail")
	public String NoticeDetail(@RequestParam("num") int num,
			@RequestParam(value="content" , defaultValue= "") String content,	
			@RequestParam(value="recontent" , defaultValue= "") String recontent, 
			@RequestParam(value="cmt_num", required = false ) Integer cmt_num,
			Model model) {
		
		String writer_id = "익명";
		
		Notice detail = noticeservice.ServiceDetail(num);
		Notice prevdetail = noticeservice.ServicePrevD(num);
		Notice nextdetail = noticeservice.ServicNextD(num);
		
		if(!content.isEmpty()) {
			noticeservice.ServiceInsertCmt(content, writer_id, num);
			System.out.println("test");
			
		}
		if(!recontent.isEmpty()) {
			noticeservice.ServiceInsertReCmt(writer_id,recontent, cmt_num);
			System.out.println("test2");
			
		}
		List<Comment> cmtlist = noticeservice.ServiceDetailCmt(num);
		List<Comment> recmtlist = noticeservice.ServiceReCmt();
		
			model.addAttribute("detail", detail);
			model.addAttribute("prevdetail", prevdetail);
			model.addAttribute("nextdetail", nextdetail);
			model.addAttribute("cmtlist", cmtlist);
			model.addAttribute("recmtlist", recmtlist);
		
		return "notice/detail";
	}
	
}

