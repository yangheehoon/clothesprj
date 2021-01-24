package web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.Member;
import web.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	MemberService memberservice = new MemberService();
	
	@RequestMapping("/login")
	public String login() {
		
		return "member/login";
	}
	      
	@RequestMapping("/logincheck")
	public String logincheck(@RequestParam("id") String id,
			@RequestParam("pw") String pw, 
			HttpServletResponse res,
			HttpServletRequest req,
			HttpSession session,
			Model model) {
		
		System.out.println(id);
		System.out.println(pw);
		Member member = memberservice.idcheck(id, pw);
		
		session.setAttribute("member", member);
		
		if(member != null) {
			Cookie cookie = new Cookie("ck", id);
			cookie.setPath("/");
			res.addCookie(cookie);
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping("/mypage")
	public String mypage(
			Model model) {
		
		return "member/mypage";
	}
	
	@RequestMapping("/logout")
	public String logout(
			HttpServletResponse res,
			Model model) {
		
			Cookie cookie = new Cookie("ck", null);
			cookie.setPath("/");
			res.addCookie(cookie);
			
		return "redirect:/home";
	}
}
