package com.sbs.khr.lolHiT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.khr.lolHiT.dao.ArticleDao;
import com.sbs.khr.lolHiT.dto.Article;
import com.sbs.khr.lolHiT.dto.Reply;
import com.sbs.khr.lolHiT.util.Util;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public int write(Map<String, Object> param) {
		articleDao.write(param);
		 
		int id = Util.getAsInt(param.get("id"));
		
		return id;
	}

	public List<Article> getForPrintArticles() {
		return articleDao.getForPrintArticles();
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	public void modify(Map<String, Object> param) {
		articleDao.modify(param);
	}

	public void delete(int id) {
		articleDao.delete(id);
	}

	public int writeReply(Map<String, Object> param) {
		articleDao.writeReply(param);
		
		int id = Util.getAsInt(param.get("id")); 
		
		return id;
	}

	public List<Reply> getForPrintReplies(int memberId, int id) {
		return articleDao.getForPrintReplies(memberId, id);
	}

	
}
