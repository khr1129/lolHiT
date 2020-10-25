package com.sbs.khr.lolHiT.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.khr.lolHiT.dto.Member;

@Mapper
public interface MemberDao {

	void join(Map<String, Object> param);

	Member getMemberById(@Param("memberId") int memberId);

	Member getMemberByLoginId(Map<String, Object> param);

	int getValidLoginIdByLoginId(Map<String, Object> param);

	void modify(Map<String, Object> param);
	
	
}
