package com.sbs.khr.lolHiT.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.khr.lolHiT.dto.Article;
import com.sbs.khr.lolHiT.service.ArticleService;
import com.sbs.khr.lolHiT.util.Util;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/write")
	public String showWrite(HttpSession session, Model model) {

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
	public String showDetail(int id, Model model) {

		Article article = articleService.getForPrintArticle(id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id, HttpSession session) {

		if (session.getAttribute("loginedMemberId") == null) {
			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");
			return "common/redirect";
		}

		Article article = articleService.getForPrintArticle(id);

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	public String doModify(@RequestParam Map<String, Object> param, HttpSession session, Model model) {

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

		articleService.modify(param);

		int id = Util.getAsInt(param.get("id"));
		
		model.addAttribute("msg", id + "번 게시물을 수정했습니다.");
		model.addAttribute("replaceUri", "../article/detail?id=" + id);
		
		return "common/redirect";

	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(int id, HttpSession session, Model model) {
		
		if (session.getAttribute("loginedMemberId") == null) {

			model.addAttribute("msg", "로그인 후 이용바랍니다.");
			model.addAttribute("replaceUri", "../member/login");

			return "common/redirect";
		}
		
		articleService.delete(id);
		
		model.addAttribute("msg", id + "번 게시물을 삭제했습니다.");
		model.addAttribute("replaceUri", "../article/list");
		
		return "common/redirect";
	}

}
