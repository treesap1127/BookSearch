package com.core.book.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "book", createIndex = true)
@Setting(settingPath = "/elasticsearch/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings.json")
public class Book {
    @Id
    private Long id;

    private Long isbn;

    private String title;

    private String author;

    private String publisher;

    private String image;

    private String description;

    private String kdc;

    private String titleSub;

    private String authorSub;
}
