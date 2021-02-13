package web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.model.Board;
import web.model.Clothes;
import web.model.NoticeView;
import web.service.BoardService;
import web.service.ClothesService;
import web.service.NoticeService;

@Controller
public class HomeController {
	
	@Autowired
	NoticeService noticeservice;
	@Autowired
	BoardService boardservice;
	@Autowired
	ClothesService clothesservice;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	  Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "test";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		
		int page =1;
		String field="title";
		String query="";
		
		List<Clothes> clotheslist = clothesservice.ServiceClothesList(query, page);
		List<NoticeView> list = noticeservice.ServiceList(page,field,query);
		List<Board> boardlist = boardservice.ServiceList(page, field, query);
		model.addAttribute("clotheslist",clotheslist);
		model.addAttribute("list",list);
		model.addAttribute("boardlist",boardlist);
		
		return "home";
	}
}