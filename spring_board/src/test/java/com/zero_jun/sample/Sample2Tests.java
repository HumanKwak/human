package com.zero_jun.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.zero_jun.domain.BoardVo;
import com.zero_jun.domain.Criteria;
import com.zero_jun.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j

public class Sample2Tests {
	@Setter @Autowired
	private BoardMapper mapper;

	
	@Test 
	public void testGetList(){
		Criteria cri = new Criteria();
		mapper.getList(new Criteria(1,10)).forEach(board->log.info(board));
	}
	@Test 
	public void testGetListPaging(){
		Criteria cri = new Criteria();
		mapper.getListwithPaging(cri).forEach(board->log.info(board));
	}
	@Test
	public void testReadExcel() throws IOException {
		FileInputStream steram = new FileInputStream("d:\\score.xlsx");
		Workbook workbook = new XSSFWorkbook(steram);
		Sheet sheet =  workbook.getSheetAt(0);
		List<String> mapNames = new ArrayList<>();
		for (int i = 0; ; i++) {
			String name = getCell(sheet,0,i).getStringCellValue();
			if(name.equals("")) break;
			mapNames.add(name);
		}
		log.info(mapNames);
		List<Map<String,Object>> list = new ArrayList<>();
		log.info(getCell(sheet, 10, 10).getCellType());
		/*for(int i = 0; ;i++){
			Map<String,Object> map = new HashMap<>();
			if(getCell(sheet,i+1,0).getCellType()==CellType.BLANK) break;
			for(int j = 0; j < mapNames.size(); j++) {
			CellType type = getCell(sheet,i+1,j).getCellType();
			switch (type) {
			case NUMERIC: case FORMULA:
				map.put(mapNames.get(j),(int)getCell(sheet, i+1, j).getNumericCellValue());
				break;
			case STRING:	
				map.put(mapNames.get(j),getCell(sheet, i+1, j).getStringCellValue());
				break;
			default:
				break;
			}
		  }	*/
	/*	log.info(map);*/
		}
		
	
	@Test
	public void testCreateExecl() throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("TEST Sheet");
		
	 Cell cell=	getCell(sheet, 0, 0);
	 cell.setCellValue("Test Value");
	 cell=	getCell(sheet, 0, 1);
	 cell.setCellValue(100);
	 
	 cell = getCell(sheet,0,2);
	 cell.setCellValue(Calendar.getInstance());
	 
	 // 1행 A,B,C
	 CellStyle style = workbook.createCellStyle();
	 style.setDataFormat(workbook.createDataFormat().getFormat("yy/MM/dd hh:mm"));
	 style.setAlignment(HorizontalAlignment.CENTER);
	 style.setVerticalAlignment(VerticalAlignment.TOP);
	 style.setFillBackgroundColor(IndexedColors.BLUE.index);
	 Font font = workbook.createFont();
	 font.setColor(IndexedColors.WHITE.index);
	 cell.setCellStyle(style);
	 
	 //셀 너비
	 sheet.autoSizeColumn(0);
	 sheet.autoSizeColumn(1);
	 sheet.autoSizeColumn(2);
	 cell = getCell(sheet,1,0);
	 cell.setCellValue(1);
	 cell = getCell(sheet,1,1);
	 cell.setCellValue(2);
	 cell = getCell(sheet,1,2);
	 cell.setCellValue("=SUM (A2:B2)");
	 
	 FileOutputStream stream = new FileOutputStream("d:\\test.xlsx");
	 workbook.write(stream);
	 workbook.close();
	}
	// row 취득
	public Row getRow(Sheet sheet, int rownum) {
		Row row = sheet.getRow(rownum);
		if (row==null) {
			row = sheet.createRow(rownum);
		}
		return row;
	}
	// cell 취득
	public Cell getCell(Row row, int cellnum){
		Cell cell = row.getCell(cellnum);
		if(cell == null) {
			cell = row.createCell(cellnum);
		}
		return cell;
	}
	public Cell getCell (Sheet sheet,int rownum, int cellnum) {
		Row row = getRow(sheet,rownum);
		return getCell(row, cellnum);
	}
	
	public static Map<String, Object> extendsBeanUtils(Object bean) {
		Map<String, Object> map = null;
		try {
			map =PropertyUtils.describe(bean);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@Test
	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<Map<String, Object>> result = mapper.getListwithPaging(new Criteria()).
		stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList());
		
		List<String> mapNames = Arrays.asList(new String[] {"bno","title","writer","regDate"});
		mapper.getListwithPaging(new Criteria())
		.stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList()).forEach(map->{
			mapNames.forEach(name->log.info(map.get(name)));
		});
	}
	
	@Test 
	public void testCreateExeclByBoard() throws IOException {
		List<Map<String, Object>> list = mapper.getListwithPaging(new Criteria()).
				stream().map(Sample2Tests::extendsBeanUtils).collect(Collectors.toList());
		List<String> mapNames = Arrays.asList(new String[] {"bno","title","writer","regDate"});
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("TEST Sheet");
		Cell cell=	getCell(sheet, 0, 0);
		cell.setCellValue("글번호");
		
		cell =	getCell(sheet, 0, 1);
		cell.setCellValue("제목");
		
		cell =	getCell(sheet, 0, 2);
		cell.setCellValue("작성자");
		
		cell =	getCell(sheet, 0, 3);
		cell.setCellValue("작성일");
		// 데이터 입력 작업
		for(int i = 1; i<list.size();i++){
			for(int j = 1; j < mapNames.size(); j++){
				cell = getCell(sheet, i+1,j);
				cell.setCellValue(list.get(i).get(mapNames.get(j)).toString());
				if(j == 3) {
					CellStyle style = workbook.createCellStyle();
					style.setDataFormat(workbook.createDataFormat().getFormat("yy/MM/dd hh:mm"));
					 cell.setCellStyle(style);
				}
			}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);

		FileOutputStream stream = new FileOutputStream("d:\\testdb.xlsx");
		workbook.write(stream);
		workbook.close();
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
