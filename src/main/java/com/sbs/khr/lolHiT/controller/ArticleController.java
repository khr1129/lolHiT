package com.sbs.khr.lolHiT.controller;

import java.util.List;
import java.util.Map;

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
	public String showWrite() {
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(@RequestParam Map<String, Object> param) {
		
		int newArticleId = articleService.write(param);
		
		return String.format("<script> alert('%d번 게시물이 작성되었습니다.'); location.replace('list'); </script>", newArticleId);
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
	public String showModify(Model model, int id) {
		
		Article article = articleService.getForPrintArticle(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(@RequestParam Map<String, Object> param) {
		
		articleService.modify(param);
		
		int id = Util.getAsInt(param.get("id"));
		
		return String.format("<script> alert('%d번 게시물을 수정했습니다.'); location.replace('detail?id=%d'); </script>", id, id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		articleService.delete(id);
		return String.format("<script> alert('%d번 게시물을 삭제했습니다.'); location.replace('../article/list'); </script>", id);
	}
	
	
}
