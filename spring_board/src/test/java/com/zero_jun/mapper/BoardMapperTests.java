package com.zero_jun.mapper;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero_jun.domain.BoardVo;
import com.zero_jun.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class BoardMapperTests {
	@Setter @Autowired
	private BoardMapper mapper;
	
	
	@Test 
	public void testGetList(){
		mapper.getList(new Criteria(1,10)).forEach(board->log.info(board));
	}
	@Test 
	public void testGetListPaging(){
		Criteria cri = new Criteria();
		cri.setType("T");
		cri.setKeyword("ㅇㄴㅁ");
		
		mapper.getListwithPaging(cri).forEach(board->log.info(board));
	}
	@Test
	public void testInsert() {
		BoardVo board =  new BoardVo();
		board.setTitle("영속 테스트 제목");
		board.setContent("영속 테스트 내용");
		board.setWriter("영속테스터");
		mapper.insert(board);
	}
	@Test
	public void testInsertSelectKey() {
		BoardVo board =  new BoardVo();
		board.setTitle("영속 테스트 제목");
		board.setContent("영속 테스트 내용");
		board.setWriter("영속테스터");
		log.info("before::" + board);
		mapper.insertSelectKey(board);
		log.info("after::" + board);
	}
	@Test
	public void testRead() {
		log.info(mapper.read(6L));
	}
	@Test
	public void testUpdate() {
		BoardVo board = new BoardVo();
		board.setTitle("수정된 영속 테스트 제목");
		board.setContent("수정된 영속 테스트 내용");
		board.setWriter("수정한 영속테스터");
		board.setBno(6L);
		log.info(mapper.update(board));
		log.info(mapper.read(6L));
	}
	@Test
	public void TestDelete() {
		log.info(mapper.read(4L));
		log.info(mapper.delete(4L));
		log.info(mapper.read(4L));
	}
	@Test
	public void testGetTotalCount() {
		Criteria cri =new Criteria();
		cri.setType("T");
		cri.setKeyword("ㅇㄴ");
		log.info(mapper.getTotalCount(cri));
	}
}
