package com.zero_jun.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero_jun.domain.ReplyVo;
import com.zero_jun.domain.ReplyCriteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	@Setter @Autowired
	private ReplyService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	@Test
	public void testGetList() {
		//service.getList().forEach(log::info);
		service.getList(new ReplyCriteria(),2701L).forEach(log::info);
	}
	@Test
	public void testRegister() {
		ReplyVo ReplyVo = new ReplyVo();
		ReplyVo.setReply("서비스 테스트 등록글 제목");
		ReplyVo.setReplyer("서비스 테스트 등록글 내용");
		ReplyVo.setBno(2701L);
		service.register(ReplyVo);
	}
	@Test
	public void testGet() {
		log.info(service.get(38L));
	}
	@Test
	public void testModify() {
		ReplyVo ReplyVo = new ReplyVo();
		ReplyVo.setReply("서비스 테스트 수정글 제목");
		ReplyVo.setReplyer("서비스 테스트 수정글 내용");
		ReplyVo.setRno(5L);
		service.modify(ReplyVo);
	}
	@Test
	public void testRemove() {
			log.info(service.get(4L));
			log.info(service.remove	(4L));
			log.info(service.get(4L));
	}
	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getSimpleName());
	}
}
