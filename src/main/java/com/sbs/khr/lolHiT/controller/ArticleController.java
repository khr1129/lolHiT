package com.sbs.khr.lolHiT.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
	
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(@RequestParam Map<String, Object> param) {
		
		System.out.println("악");
		System.out.println("악222");
		
		System.out.println("param 안에는 무엇이? : " + param);
		System.out.println("param이 제대로 가지고 오고 있습니다. 이어서 시작하기.");
		
		return "<script> alert('테스트 중입니다.');   location.replace('/usr/home/main'); </script> ";
	}
}
