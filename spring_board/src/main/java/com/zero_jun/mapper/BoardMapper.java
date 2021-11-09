package com.zero_jun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zero_jun.domain.BoardVo;
import com.zero_jun.domain.Criteria;

public interface BoardMapper {
/*	@Select("SELECT * FROM TBL_BOARD WHERE BNO>0")*/
	public List<BoardVo> getList(Criteria cri);
	public List<BoardVo> getListwithPaging(Criteria cri);
	void insertSelectKey(BoardVo board);
	void insert(BoardVo board);
	BoardVo read(Long bno);
	int update(BoardVo boardVo);
	int delete(Long bno);
	int getTotalCount(Criteria cri);
	void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}

