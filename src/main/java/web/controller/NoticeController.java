package web.controller;

import java.util.List;

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
			Model model) {
		
		Notice detail = noticeservice.ServiceDetail(num);
		Notice prevdetail = noticeservice.ServicePrevD(num);
		Notice nextdetail = noticeservice.ServicNextD(num);
		List<Comment> cmtlist = noticeservice.ServiceDetailCmt(num);
		
			model.addAttribute("detail", detail);
			model.addAttribute("prevdetail", prevdetail);
			model.addAttribute("nextdetail", nextdetail);
			model.addAttribute("cmtlist", cmtlist);
		
		return "notice/detail";
	}
	
}

