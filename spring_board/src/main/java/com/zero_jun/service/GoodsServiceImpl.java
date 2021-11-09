package com.zero_jun.service;

import org.springframework.stereotype.Service;

import com.zero_jun.domain.GoodsVo;
import com.zero_jun.mapper.GoodsMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {
	private GoodsMapper goodsMapper;
	
	public void registar(GoodsVo vo) {
		goodsMapper.insert(vo);
		vo.getAttachs().forEach(goodsMapper::insertAttach);
		}

}
