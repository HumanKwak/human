package com.zero_jun.service;

import java.util.List;

import com.zero_jun.domain.ReplyCriteria;
import com.zero_jun.domain.ReplyVo;

public interface ReplyService {
	void register(ReplyVo vo);
	ReplyVo get(Long rno);
	boolean modify(ReplyVo vo);
	boolean remove(Long rno);
	List<ReplyVo> getList(ReplyCriteria cri,Long bno);
}
