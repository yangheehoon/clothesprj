package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

	@RequestMapping("list")
	public String NoticeList(Model model) {
		
		return "notice/noticelist";
	}
}
