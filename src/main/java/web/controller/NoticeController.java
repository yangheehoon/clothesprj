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
			
			model.addAttribute("list", list);
			model.addAttribute("count", count);
		
		return "notice/list";
	}
	
	@RequestMapping("/detail")
	public String NoticeDetail(@RequestParam("num") int num,
			@RequestParam(value="cmt_content" , defaultValue= "") String cmt_content,	
			@RequestParam(value="recontent" , defaultValue= "") String recontent, 
			@RequestParam(value="cmt_num", required = false ) Integer cmt_num,
			@RequestParam(value="writer_id", required = false ) String writer_id,
			Model model) {
		
		if(!cmt_content.isEmpty()&&writer_id!=null) { //isEmpty 반환값 =="" 
			noticeservice.ServiceInsertCmt(cmt_content, writer_id, num);
			System.out.println("NoticeInsertCmt");			
		}
		if(!recontent.isEmpty()&&writer_id!=null) {
			noticeservice.ServiceInsertReCmt(writer_id,recontent, cmt_num);
			System.out.println("NoticeInsertReCmt");			
		}
		
		noticeservice.ServiceUpdateHit(num);
		Notice detail = noticeservice.ServiceDetail(num);
		Notice prevdetail = noticeservice.ServicePrevD(num);
		Notice nextdetail = noticeservice.ServicNextD(num);		
		
		int cmt_count = noticeservice.ServiceCmtCount(num);
		List<Comment> cmtlist = noticeservice.ServiceDetailCmt(num);
		List<Comment> recmtlist = noticeservice.ServiceReCmt();
		
			model.addAttribute("detail", detail);
			model.addAttribute("prevdetail", prevdetail);
			model.addAttribute("nextdetail", nextdetail);
			model.addAttribute("cmtlist", cmtlist);
			model.addAttribute("recmtlist", recmtlist);
			model.addAttribute("cmt_count", cmt_count);
		
		return "notice/detail";
	}
	
	@RequestMapping("/add")
	public String AddNotice() {
			
		return "notice/add";
	}
	@RequestMapping("/add2")
	public String AddNotice2(@RequestParam(value="title" , required=false) String title,
			@RequestParam(value="content" , required=false) String content,			
			@RequestParam(value="files" , defaultValue="") String files,			
			@RequestParam(value="writer_id" , required=false) String writer_id,						
			Model model) {
			
		if(title!=null && content!=null && writer_id!=null) {
			noticeservice.ServiceInsertNotice(title, writer_id, content, files);
			System.out.println("insertnotice");
		}
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping("/notice_del")   //서브밋
	public String DelNotice(@RequestParam("delnum") int delnum) {
		
			noticeservice.ServiceDelNotice(delnum);
			System.out.println("delnotice");
		
		return "redirect:/notice/list";
	}
	
	@RequestMapping("/notice_cmt_del")   //ajax
	public String DelCmt(@RequestParam("cmtnum") int cmtnum) {
		
			noticeservice.ServiceDelCmt(cmtnum);
			System.out.println("delnoticecmt");
		
		return "notice/list";
	}
	
	@RequestMapping("/notice_recmt_del")   //ajax
	public String DelReCmt(@RequestParam("recmtnum") int recmtnum) {
		
			noticeservice.ServiceDelReCmt(recmtnum);
			System.out.println("delnoticerecmt");
		
		return "notice/list";
	}
}

