package com.core.module.book.vo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.Data;

@Data
@Document(indexName = "book", createIndex = true)
@Setting(settingPath = "/elasticsearch/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings.json")
public class Book {
	@Id
    private Long isbn;

    private String title;

    private String author;

    private String publisher;

    private String image;

    private String description;

    private Double kdc;

    private String titleSub;

    private String authorSub;
    
}