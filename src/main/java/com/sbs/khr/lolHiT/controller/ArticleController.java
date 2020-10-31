package com.sbs.khr.lolHiT.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.khr.lolHiT.dto.Article;
import com.sbs.khr.lolHiT.dto.Member;
import com.sbs.khr.lolHiT.dto.Reply;
import com.sbs.khr.lolHiT.service.ArticleService;
import com.sbs.khr.lolHiT.service.MemberService;
import com.sbs.khr.lolHiT.util.Util;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/article/write")
	public String showWrite(HttpSession session, Model model) {
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if (session.getAttribute("loginedMemberId") == null) {

			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");

			return "common/redirect";
		}

		return "usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	public String doWrite(@RequestParam Map<String, Object> param, HttpSession session, Model model) {

		if (param.isEmpty()) {
			model.addAttribute("msg", "게시물을 다시 작성바랍니다.");
			model.addAttribute("replaceUri", "../article/list");
			return "common/redirect";
		}

		if (session.getAttribute("loginedMemberId") == null) {

			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");

			return "common/redirect";
		}

		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		param.put("memberId", loginedMemberId);
		int newArticleId = articleService.write(param);

		model.addAttribute("msg", newArticleId + "번 게시물이 작성되었습니다.");
		model.addAttribute("replaceUri", "../article/list");

		return "common/redirect";
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {

		List<Article> articles = articleService.getForPrintArticles();

		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(int id, Model model, HttpServletRequest req) {

		Article article = articleService.getForPrintArticle(id);
		
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		
		List<Reply> replies = articleService.getForPrintReplies(loginedMemberId, article.getId());

		model.addAttribute("article", article);
		model.addAttribute("replies", replies);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id, HttpSession session) {
		
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		Member member = memberService.getMemberById(loginedMemberId);

		Article article = articleService.getForPrintArticle(id);

		
		if (article.getMemberId() != member.getId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if (session.getAttribute("loginedMemberId") == null) {
			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");
			return "common/redirect";
		}

		

	
		
		
		

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	public String doModify(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		
		int id = Util.getAsInt(param.get("id"));
		
		if (param.isEmpty()) {
			model.addAttribute("msg", "게시물을 다시 작성바랍니다.");
			model.addAttribute("replaceUri", "../article/list");
			return "common/redirect";
		}

		int loginedMemberId = 0;
		
		Article article = articleService.getForPrintArticle(id);
		Member member = memberService.getMemberById(loginedMemberId);

		if (article.getId() != member.getId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		if (session.getAttribute("loginedMemberId") == null) {

			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");

			return "common/redirect";
		}

		

		articleService.modify(param);

		

		model.addAttribute("msg", id + "번 게시물을 수정했습니다.");
		model.addAttribute("replaceUri", "../article/detail?id=" + id);

		return "common/redirect";

	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(int id, HttpSession session, Model model) {
		
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}

		if (session.getAttribute("loginedMemberId") == null) {

			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");

			return "common/redirect";
		}
		
		Article article = articleService.getForPrintArticle(id);
		Member member = memberService.getMemberById(loginedMemberId);
		
		if ( article.getMemberId() != member.getId() ) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.delete(id);
		

		model.addAttribute("msg", id + "번 게시물을 삭제했습니다.");
		model.addAttribute("replaceUri", "../article/list");

		return "common/redirect";
	}
	
	@RequestMapping("/usr/article/doWriteReply")
	public String doWriteReply(@RequestParam Map<String, Object> param, Model model) {
		
		int newReplyId = articleService.writeReply(param);
		int articleId = Util.getAsInt(param.get("articleId"));
		
		model.addAttribute("msg", String.format("%d번 댓글이 생성되었습니다.", newReplyId));
		model.addAttribute("replaceUri", String.format("../article/detail?id=%d", articleId));
		
		return "common/redirect";
	}

}
