
package com.sbs.khr.lolHiT.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// config는 문지기, 안내자

	// beforeActionInterceptor 인터셉터 불러오기

	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("needToLoginInterceptor") HandlerInterceptor
	 * needToLoginInterceptor;
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforActionInterceptor가 모든 액션이 실행 되기 전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");

		// 로그인 없이도 접속할 수 있는 URI를 전부 기술
		/*
		 * registry.addInterceptor(needToLoginInterceptor).addPathPatterns("/**").
		 * excludePathPatterns("/")
		 * .excludePathPatterns("/usr/home/main").excludePathPatterns(
		 * "/usr/article/list") .excludePathPatterns("/usr/article/detail");
		 */

	}

}
