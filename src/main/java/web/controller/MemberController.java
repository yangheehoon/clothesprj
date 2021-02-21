package web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import web.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private ServletContext ctx;
	
	@Autowired
	MemberService memberservice;
	
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
						
		memberservice.ServiceJoin(id, pw, nickname, name, birth, email, gender, phone_num);
		
		return "redirect:/member/success";
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
	
	
	@RequestMapping("/ChangeMember")
	public String ChangeMember(@RequestParam(value="nick", required=false) String nickname,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="phone", required=false) String phone_num,
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="pw", required=false) String pw,
			@RequestParam(value="profileImg", required=false) MultipartFile profileImg,
			HttpSession session) throws IllegalStateException, IOException {
					
		if(nickname!=null&&email!=null&&phone_num!=null&&id!=null&&pw!=null&&profileImg.isEmpty()) {			
			memberservice.ServiceChangeMember(nickname,email,phone_num,id,pw,session);
			return "redirect:/member/mypage";
			
		}else if(nickname!=null&&email!=null&&phone_num!=null&&id!=null&&pw!=null&&!profileImg.isEmpty()){
			
			String filename = profileImg.getOriginalFilename();		
			String realpath= ctx.getRealPath("/resources/member");
			System.out.println(realpath);
			realpath += File.separator + filename;
			File savefile = new File(realpath);
			profileImg.transferTo(savefile);
			String pro_file= filename;
			
			memberservice.ServiceChangeMember2(nickname,email,phone_num,pro_file,id,pw,session);
			return "redirect:/member/mypage";
		}
		
		return "member/ChangeMember";
	}
	
	
	@RequestMapping("/ChangePw")
	public String ChangePw(@RequestParam(value="newpw",required=false) String pw, 
			@RequestParam(value="id",required=false) String id,
			HttpSession session) {
			if(pw!=null) {
				memberservice.ServiceChangePw(pw,id);
				session.removeAttribute("member");
				return "redirect:/member/login";
			}
		return "member/ChangePw";
	}
		
	
	@RequestMapping("/CheckDel")
	public String CheckDel() {
		return "member/CheckDel";
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
			
			memberservice.ServiceDelMember(id, pw);
			session.invalidate();
		
		return "redirect:/member/goodbye";
	}
	
	@RequestMapping("/goodbye")
	public String goodbye() {
		return "member/goodbye";
	}
}
