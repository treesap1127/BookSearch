package com.core.elasitcSearch.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class IndexVo {
	private String indexName;
	private String fileName;
	private String keyword;
	private int indexSeq;
	private MultipartFile excelFile;
	
}