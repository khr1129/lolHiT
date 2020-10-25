package com.sbs.khr.lolHiT.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.khr.lolHiT.dao.MemberDao;
import com.sbs.khr.lolHiT.dto.Member;
import com.sbs.khr.lolHiT.util.Util;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public int join(Map<String, Object> param) {
		
		memberDao.join(param);
		
		int id = Util.getAsInt(param.get("id"));
		
		return id;
	}

	public Member getMemberById(int memberId) {
		return memberDao.getMemberById(memberId);
	}


	public Member getMemberByLoginId(Map<String, Object> param) {
		return memberDao.getMemberByLoginId(param);
	}

	public int getValidLoginIdByLoginId(Map<String, Object> param) {
		return memberDao.getValidLoginIdByLoginId(param);
	}

	public void modify(Map<String, Object> param) {
		memberDao.modify(param);
	}

	
	
}
