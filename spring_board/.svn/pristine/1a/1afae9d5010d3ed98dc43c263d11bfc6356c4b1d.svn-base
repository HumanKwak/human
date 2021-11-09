package com.zero_jun.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.zero_jun.controller.UploadController;
import com.zero_jun.domain.AttachVo;
import com.zero_jun.mapper.AttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Component @Log4j @AllArgsConstructor
public class FileCheckTask {
	private AttachMapper attachMapper;
	
	private Set<String> getFolderBefore(List<AttachVo> fileList){
		return new HashSet<>(fileList.stream().map(vo->vo.getPath()).collect(Collectors.toSet()));
	}
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() throws Exception{
		//DB list
		List<AttachVo> fileList = attachMapper.getOldFiles();
		log.info(fileList);
		List<Path> fileListPaths = 
				fileList
				.stream()
				.map(vo->
						Paths.get(UploadController.getUploadFolder(), vo.getFullPath()))
				.collect(Collectors.toList());
		
		
		//thumbnail 여부 확인 후 예상 파일 목록에 섬네일 파일도 추가
		fileList
		.stream()
		.filter(vo->vo.isImage())
		.map(vo -> Paths.get(UploadController.getUploadFolder(),vo.getThumb()))
		.forEach(fileListPaths::add);
		log.info(fileListPaths);
		//thumbnail 여부 끝
		
		getFolderBefore(fileList).forEach(log::info);
		// 물리적 파일 list
		getFolderBefore(fileList).forEach(folder -> {
			File targetDir = Paths.get(UploadController.getUploadFolder(),folder).toFile();
			log.info(targetDir);
			File[] removeFiles = targetDir.listFiles(file->!fileListPaths.contains(file.toPath()));
			log.info(removeFiles);
			Arrays.asList(removeFiles).forEach(file->file.delete());
		});
		//log.info(fileList);

	}
}
