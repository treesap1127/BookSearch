package com.core.module.book.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;


@Data
@Document(indexName = "book", createIndex = true)
@Setting(settingPath = "/elasticsearch/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings.json")
public class Book {
    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long isbn;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Long)
    private Long publisher;

    @Field(type = FieldType.Text)
    private String image;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Long)
    private Long kdc;

    @Field(type = FieldType.Text)
    private String titleSub;

    @Field(type = FieldType.Text)
    private String authorSub;

}
