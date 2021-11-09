package com.zero_jun.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zero_jun.service.SalesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@Controller @AllArgsConstructor
public class SalesController {
	private SalesService salesService;
	
	@ResponseBody @GetMapping("sales/{year}")
	public List<Map<String, Object>> getSalesListBy(@PathVariable String year) {
		log.info(year);
		return salesService.getSalesListBy(year);
	}
	@GetMapping("sales/chart/{year}")
	public String chart(@ModelAttribute @PathVariable String year) {
		log.info("chart!!" + year);
		return "sales/chart";
	}
}
