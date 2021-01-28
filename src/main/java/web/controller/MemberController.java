package web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String idcheck(@RequestParam(value="id") String id ,HttpSession session) {
		
		String pw="x";
		return memberservice.ServiceIdCheck(id,pw,session); 		
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
						
		return	memberservice.ServiceJoin(id, pw, nickname, name, birth, email, gender, phone_num);
				
	}
	
	@RequestMapping("/success")
	public String success() {
		return "member/success";
	}
	
	
	@RequestMapping("/fail")
	public String fail() {
		return "member/fail";
	}	
	
	
	@RequestMapping("/login")
	public String login() {
		
		return "member/login";
	}
	
	@ResponseBody
	@RequestMapping("/logincheck")
	public String logincheck(@RequestParam("id") String id,
			@RequestParam("pw") String pw, 
			HttpSession session) {		
		
		System.out.println(id);
		System.out.println(pw);
		
		return memberservice.ServiceIdCheck(id, pw, session);
			
	}
	
	
	@RequestMapping("/mypage")
	public String mypage() {
		
		return "member/mypage";
	}
	
	@RequestMapping("/logout")
	public String logout(
			HttpSession session) {
		
			//session.removeAttribute("member");
			session.invalidate();
			//Cookie cookie = new Cookie("ck", null);
			//cookie.setPath("/");
			//res.addCookie(cookie);
			
		return "redirect:/home";
	}
	
	@RequestMapping("/pwchange")
	public String pwchange(@RequestParam(value="newpw",required=false) String pw, 
			@RequestParam(value="id",required=false) String id,
			HttpSession session) {
			if(pw!=null) {
				memberservice.ServiceChangePw(pw,id);
				session.removeAttribute("member");
				return "redirect:/member/login";
			}
		return "member/pwchange";
	}
		
	
	@RequestMapping("/delcheck")
	public String delcheck() {
		return "member/delcheck";
	}
	
	@ResponseBody
	@RequestMapping("/pwcheck")
	public String pwcheck(@RequestParam("id") String id,
			@RequestParam("pw") String pw) {
		return memberservice.ServicePwCheck(id,pw);
	}
	
	@RequestMapping("/del")
	public String  del(@RequestParam("id") String id,
			@RequestParam("pw") String pw, HttpSession session) {
		
			System.out.println(id);
			System.out.println(pw);
			
			memberservice.ServiceDelMember(id, pw);
			session.invalidate();
		
		return "redirect:/member/goodbye";
	}
	
	@RequestMapping("/goodbye")
	public String goodbye() {
		return "member/goodbye";
	}
}
