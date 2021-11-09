package com.zero_jun.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zero_jun.domain.ReplyVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReplyControllerTests {
	@Autowired @Setter
	private BoardController controller;
	
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;
	
	@org.junit.Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test
	public void testCreate() throws Exception {
		ReplyVo vo = new ReplyVo();
		vo.setBno(2697L);
		vo.setReply("테스트 새 댓글 제목");
		vo.setReplyer("테스터");
		
		String jsonStr = new Gson().toJson(vo);
		log.info("jsonStr::"+jsonStr);
		
		mvc.perform(post("/replies/new")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr))
				.andExpect(status().is(200));
				
	}
	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	@Test
	public void testList() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "4")
				)
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		//log.info(map);
		List<?> list = (List<?>) map.get("list");
		list.forEach(log::info);
		
	}
	@Test
	public void testGet() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "18"))
		.andReturn()
		.getModelAndView()
		.getModelMap();
		

		log.info(map.get("board"));
		
	}
	@Test
	public void testRegister() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "테스트 새글 제목")
					.param("content", "테스트 새글 내용")
					.param("writer", "user00")
				)
		.andReturn()
		.getModelAndView();
		
		log.info(mav.getViewName());
		
	}
	@Test
	public void testModify() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/board/modify")
					.param("title", "테스트 수정글 제목")
					.param("content", "테스트 수정글 내용")
					.param("writer", "modify01")
					.param("bno","49")
				)
		.andReturn()
		.getModelAndView();
		
		log.info(mav.getViewName());
		
	}
	@Test
	public void testRemove() throws Exception {
		ModelAndView mav = mvc.perform(
					MockMvcRequestBuilders.post("/board/remove")
					.param("bno","18")
				)
				.andReturn()
				.getModelAndView();
		
		log.info(mav.getViewName());
		
	}
	
	/*
	 * @Test public void testGetTotal { log.info(service.getTotal(new Criteria()));
	 * }
	 */
	
}
