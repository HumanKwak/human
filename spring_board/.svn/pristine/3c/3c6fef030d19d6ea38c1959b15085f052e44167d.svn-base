package com.zero_jun.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero_jun.domain.ReplyCriteria;
import com.zero_jun.domain.ReplyVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Setter @Autowired
	private ReplyMapper mapper;
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	@Test
	public void testInsert() {
		IntStream.range(0, 25).forEach(i->{
			ReplyVo vo = new ReplyVo();
			
			vo.setBno(2697L);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("댓글러");
			
			mapper.insert(vo);
		});
	}
	

	@Test
	public void testRead() {
		log.info(mapper.read(3L));
	}
	@Test
	public void testUpdate() {
		ReplyVo vo = new ReplyVo();
		vo.setReply("테스트용 코드");
		vo.setReplyer("테스터");
		vo.setRno(3L);
		mapper.update(vo);
	}
	@Test
	public void testDelete() {
		log.info(mapper.delete(2L));
	}
	@Test
	public void testGetList() {
		ReplyCriteria criteria = new ReplyCriteria();
		criteria.setLastRno(11L);
		mapper.getList(2697L,criteria).forEach(log::info);
	}
}
