package com.zero_jun.mapper;

import java.util.List;
import java.util.Map;

public interface SalesMapper {
	List<Map<String,Object>> getSalesListBy(String year);
}
