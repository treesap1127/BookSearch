package com.core.elasitcSearch.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class IndexVo {
	private String indexName;
	private String fileName;
	private String keyword;
	private int indexSeq;
	private MultipartFile excelFile;
	
}