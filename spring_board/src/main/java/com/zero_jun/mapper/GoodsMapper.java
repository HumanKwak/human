package com.zero_jun.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.zero_jun.domain.GoodsVo;

public interface GoodsMapper {
	@Insert("INSERT INTO TBL_MUSINSA VALUES(#{bno},#{title},#{price},#{brand})")
	void insert(GoodsVo vo);
	@Insert("INSERT INTO TBL_MUSINSA_ATTACH VALUES(#{bno},#{ordered})")
	void insertAttach(Map<String,Object> map);
}
