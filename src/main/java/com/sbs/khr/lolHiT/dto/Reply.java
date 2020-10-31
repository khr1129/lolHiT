package com.sbs.khr.lolHiT.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int articleId;
	private int memberId;
	private String body;
	private Map<String, Object> extra;
}
