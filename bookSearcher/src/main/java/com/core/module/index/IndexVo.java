package com.core.module.index;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("indexCnt")
public class IndexVo {
	private String indexName;
	private String keyword;
	private int indexSeq;
	private int indexCnt;
	private MultipartFile excelFile;
	
}
