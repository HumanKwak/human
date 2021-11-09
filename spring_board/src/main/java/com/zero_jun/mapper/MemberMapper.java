package com.zero_jun.mapper;

import java.util.Map;

import com.zero_jun.domain.MemberVo;

public interface MemberMapper {
	void insertMember(Map<String, Object> map);
	void insertAuth(Map<String, Object> map);
	MemberVo read(String userid);
}
