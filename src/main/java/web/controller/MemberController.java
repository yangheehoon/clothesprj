package web.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import web.model.Member;
import web.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	MemberService memberservice = new MemberService();
	
	@RequestMapping("/joinform")
	public String joinform(	
			Model model) {
		
		return "member/join";
	}	
	
	@ResponseBody
	@RequestMapping("/idcheck")
	public String idcheck(@RequestParam(value="id") String id) {
		
		System.out.println(id);
		
		return memberservice.Serviceidcheck(id); 		
	}
	
	@RequestMapping("/join")
	public String join(@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("nick") String nickname,
			@RequestParam("name") String name,
			@RequestParam("birth") int birth,
			@RequestParam("email") String email,
			@RequestParam("gen") String gender,
			@RequestParam("phone") String phone_num
			) {
			
			
			//memberservice.ServiceJoin(id, pw, nickname, name, birth, email, gender, phone_num);
		
		return "redirect:/member/success";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "member/success";
	}
	
	
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
