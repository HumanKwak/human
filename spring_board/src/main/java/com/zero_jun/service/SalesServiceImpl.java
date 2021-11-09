package com.zero_jun.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zero_jun.mapper.SalesMapper;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class SalesServiceImpl implements SalesService {
	private SalesMapper salesMapper;
	@Override
	public List<Map<String, Object>> getSalesListBy(String year) {
		return salesMapper.getSalesListBy(year);
	}

}
