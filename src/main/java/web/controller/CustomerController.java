package web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.model.Clothes;
import web.service.ClothesService;

@Controller
/*@SessionAttributes("nologin")*/
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ServletContext ctx;
	
	ClothesService clothesservice = new ClothesService();	
	
	List<Clothes> temp = new ArrayList<Clothes>();	
	
	@RequestMapping("/clotheslist")
	public String ClothesList(@RequestParam(value="q", defaultValue="")String query,
			@RequestParam(value="p",defaultValue="1") int page,
			Model model) {   
	
		List<Clothes> clotheslist = clothesservice.ServiceClothesList(query,page);
		int clothescount = clothesservice.ServiceClothesCount(query);
		
		model.addAttribute("clotheslist", clotheslist);
		model.addAttribute("clothescount", clothescount);
		
	return "customer/clotheslist";
	}	
		
	@RequestMapping("/clothesadd")
	public String AddClothes() {
		
		return "customer/clothesadd";
	}
	
	@RequestMapping("/clothesadd2")
	public String AddClothes2(@RequestParam(value="n")String name,
			@RequestParam(value="p" , defaultValue="99999999")int price,
			@RequestParam(value="d")String description,
			@RequestParam(value="f")MultipartFile reqfiles,
			Model model	) throws IllegalStateException, IOException {
			
			String files = reqfiles.getOriginalFilename();
		
			String webpath="/resources/customer/clothes";
			String realpath= ctx.getRealPath(webpath);
			System.out.println(realpath);
			realpath += File.separator + files;
			File savefile = new File(realpath);
			reqfiles.transferTo(savefile);
			
			
			clothesservice.ServiceInsertClothes(name, price, description, files);
		
		return "redirect:/customer/clotheslist";
	}
	
	@RequestMapping("/clothesdetail")
	public String clothesDetail(@RequestParam("num") int num,
			Model model) {
		
			Clothes clothes = clothesservice.ServiceClothesDetail(num);
			
			model.addAttribute("clothes", clothes);
		
		return "customer/clothesdetail";
	}
	
	@RequestMapping("/cart")
	public String cart(
			Model model) {
		
			return "customer/cart";
	}
	
	@RequestMapping("/addcart")
	public String addcart(@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("name") String name,
			@RequestParam("price") int price,
			@RequestParam("files") String files,
			@RequestParam("description") String description,
			@RequestParam("num") int num,
			HttpSession session,
			//HttpServletRequest req,
			//HttpServletResponse res,
			Model model) throws UnsupportedEncodingException {						
		
			if(session.getAttribute("nologin")==null) {
			temp = new ArrayList<Clothes>();	
			}
			Date regdate =null;
	
			Clothes cl = new Clothes(num, name, price, description, color, size, files, regdate);
					
			temp.add(cl);
			
			session.setAttribute("nologin", temp);
		
		
		/*	Cookie[] cookies = req.getCookies();
			if(cookies != null) {
				if("nologin"==cookies[0].getName()) {
					if(session.getAttribute("nologin")==null) {
						temp = new ArrayList<Clothes>();	
						}
						Date regdate =null;
					
						Clothes cl = new Clothes(num, name, price, description, color, size, files, regdate);
								
						temp.add(cl);
						
						session.setAttribute("nologin", temp);
				}
			}
		*/
			/*String nologin = null;
			Cookie cookie =new Cookie("nologin", nologin);	
			res.addCookie(cookie);*/
		
		
		return "redirect:/customer/clothesdetail?num="+num;
	}
	
	@RequestMapping("/delcart")
	public String delcart(HttpSession session) {
		
		session.invalidate();
		
		System.out.println(session);
		
		return "customer/cart";
	}
			
	@RequestMapping("/pay")
	public String pay(@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("name") String name,
			Model model) {
		
		System.out.println(color);
			
		return "customer/pay";
	}
}
