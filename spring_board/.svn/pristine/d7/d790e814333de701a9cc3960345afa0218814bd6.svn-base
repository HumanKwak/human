package com.zero_jun.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zero_jun.domain.ReplyCriteria;
import com.zero_jun.domain.ReplyVo;
import com.zero_jun.mapper.BoardMapper;
import com.zero_jun.mapper.ReplyMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor @Service
public class ReplyServiceImpl implements ReplyService{
	private ReplyMapper mapper;
	private BoardMapper boardMapper;
	
	@Override
	@Transactional
	public void register(ReplyVo vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		//작업 1
		mapper.insert(vo);
		//작업2
		
	}

	@Override
	public ReplyVo get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean modify(ReplyVo vo) {
		return mapper.update(vo)>0;
	}

	@Transactional
	@Override
	public boolean remove(Long rno) {
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return mapper.delete(rno)>0;
	}

	@Override
	public List<ReplyVo> getList(ReplyCriteria cri, Long bno) {
		return mapper.getList(bno, cri);
	}

}
