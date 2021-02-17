package web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.AclEntry.Builder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.model.Comment;
import web.model.Notice;
import web.model.NoticeView;
import web.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private ServletContext ctx;
	
	@Autowired
	NoticeService noticeservice;
	
	@RequestMapping("/list")
	public String NoticeList(@RequestParam(value="p" , defaultValue="1") int page, 
			@RequestParam(value="f" , defaultValue="title") String field,
			@RequestParam(value="q" , defaultValue="") String query,
			Model model) throws ClassNotFoundException, SQLException {
		
		    List<NoticeView> list = noticeservice.ServiceList(page,field,query);
			int count = noticeservice.ServiceListCount(field ,query);
			
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
		
		if(!cmt_content.isEmpty()&&writer_id!=null) { 
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
			@RequestParam(value="writer_id" , defaultValue="") String writer_id,						
			@RequestParam(value="files")MultipartFile[] reqfiles,
			Model model) throws IllegalStateException, IOException {
		
		StringBuilder builder = new StringBuilder();
			
		for(MultipartFile reqfile : reqfiles) {
			if(reqfile.getSize()==0) {
				continue;
			}
			String filename = reqfile.getOriginalFilename();
			builder.append(filename+",");
			String realpath= ctx.getRealPath("/resources/notice");
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

