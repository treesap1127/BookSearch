package com.core.module.index.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("IndexVo")
public class IndexVo {
	private String indexName;
	private String fileName;
	private String keyword;
	private int indexSeq;
	private MultipartFile excelFile;
	
}
