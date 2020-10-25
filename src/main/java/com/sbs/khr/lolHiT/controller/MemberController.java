package com.sbs.khr.lolHiT.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.khr.lolHiT.dto.Member;
import com.sbs.khr.lolHiT.service.MemberService;
import com.sbs.khr.lolHiT.util.Util;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		
		int newMemberId = memberService.join(param);
		
		Member member = memberService.getMemberById(newMemberId);
		
		return String.format("<script> alert('%s님, 회원가입을 축하드립니다.'); location.replace('../home/main'); </script>", member.getName());
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session) {
		
		int validLoginId = 0;
		
		if ( param.get("loginId") != null ) {
			validLoginId = memberService.getValidLoginIdByLoginId(param);
		}
		
		String loginPw = Util.getAsStr(param.get("loginPw"), "");
		
		Member member = memberService.getMemberByLoginId(param);
		
		if ( validLoginId == 0 ) {
			return "<script> alert('존재하지 않는 아이디 입니다.'); history.back(); </script>";
		}
		
		if ( loginPw.equals(member.getLoginPw()) == false ) {
			return "<script> alert('비밀번호가 일치하지 않습니다.'); history.back(); </script>";
		}
		
		session.setAttribute("loginedMemberId", member.getId());
		
		return String.format("<script> alert('%s님, 환영합니다.'); location.replace('../home/main'); </script>", member.getName()); 
		
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		if ( session != null ) {
			session.removeAttribute("loginedMemberId");
			return "<script> location.replace('../home/main'); </script>";
		}
		else {
			
		}
		return "";
	}
	
	@RequestMapping("/usr/member/modify")
	public String showModify(HttpServletRequest req, Model model) {
		
		return "usr/member/modify";
		
	}
	
	@RequestMapping("/usr/member/doModify")
	public String doModify(@RequestParam Map<String, Object> param, Model model, HttpServletRequest req) {
		
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		param.put("id", loginedMemberId);
		
		param.remove("loginId");
		param.remove("loginPw");
		
		
		memberService.modify(param);
		
		model.addAttribute("msg", "이름이 수정되었습니다.");
		model.addAttribute("replaceUri", "../home/main");
		
		return "common/redirect";
	}
	
	
}
