package com.zero_jun.service;

import java.util.List;

import com.zero_jun.domain.AttachVo;
import com.zero_jun.domain.BoardVo;
import com.zero_jun.domain.Criteria;

public interface BoardService {
	void register(BoardVo boardVo);
	BoardVo get(Long bno);
	boolean modify(BoardVo boardVo);
	boolean remove(Long bno);
	List<BoardVo> getList(Criteria cri);
	int getTotal(Criteria cri);
	List<AttachVo> getAttachs(Long bno);
}
