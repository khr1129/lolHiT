package com.sbs.khr.lolHiT.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.khr.lolHiT.dto.Article;

@Mapper
public interface ArticleDao {

	void write(Map<String, Object> param);

	List<Article> getForPrintArticles();

	Article getForPrintArticle(@Param("id") int id);

	void modify(Map<String, Object> param);

	void delete(@Param("id") int id);
	
}
