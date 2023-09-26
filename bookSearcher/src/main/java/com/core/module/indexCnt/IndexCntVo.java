package com.core.module.indexCnt;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("indexCnt")
public class IndexCntVo {
	private String indexName;
	private int indexSeq;
	private int indexCnt;
	private MultipartFile excelFile;
	
}
