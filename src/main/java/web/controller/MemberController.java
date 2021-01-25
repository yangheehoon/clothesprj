package web.controller;

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
			HttpSession session,
			Model model) {
		
		session.removeAttribute("cart");
		
		
		if(memberservice.idcheck(id, pw)==false) {
			model.addAttribute("msg", "아이디혹은 비밀번호가 일치하지 않습니다");
			return "member/login";
		}else {			
			Member member = memberservice.logincheck(id, pw);			
			session.setAttribute("member", member);
			return "redirect:/home";
		}				
		/*if(member != null) {
			Cookie cookie = new Cookie("ck", id);
			cookie.setPath("/");
			res.addCookie(cookie);
		}*/				
	}
	
	
	@RequestMapping("/mypage")
	public String mypage() {
		
		return "member/mypage";
	}
	
	@RequestMapping("/logout")
	public String logout(
			HttpSession session,
			Model model) {
		
			//session.removeAttribute("member");
			session.invalidate();
			//Cookie cookie = new Cookie("ck", null);
			//cookie.setPath("/");
			//res.addCookie(cookie);
			
		return "redirect:/home";
	}
}
