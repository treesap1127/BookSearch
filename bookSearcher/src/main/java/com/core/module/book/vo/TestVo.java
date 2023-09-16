package com.core.module.book.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(indexName = "post")
@Getter
@NoArgsConstructor
@ToString
public class TestVo {
	@Id
	private String id;
	private String content;

	public TestVo(String id, String content) {
		this.id = id;
		this.content = content;
	} 
}