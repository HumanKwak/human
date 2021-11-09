package com.zero_jun.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data @AllArgsConstructor
public class Criteria {
	private int PageNum;
	private int amount;
	private String type;
	private String keyword;
	private int category = 1;
	
	public Criteria(){
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		PageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
			return type == null ? new String[] {}	: type.split("");
	}
	
	public String getParams() {
		return UriComponentsBuilder.newInstance()
				.queryParam("PageNum",PageNum)
				.queryParam("amount",amount)
				.queryParam("type",type)
				.queryParam("keyword",keyword)
				.build().toString();
	}
}
