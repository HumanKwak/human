package com.zero_jun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zero_jun.domain.ReplyCriteria;
import com.zero_jun.domain.ReplyVo;

public interface ReplyMapper {
	int insert(ReplyVo vo);
	ReplyVo read(Long rno);
	int update(ReplyVo vo);
	int delete(Long rno);
	List<ReplyVo> getList(@Param("bno") Long bno,@Param("cri") ReplyCriteria cri);
}
