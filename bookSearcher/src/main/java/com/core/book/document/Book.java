package com.core.book.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Map;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "book", createIndex = true)
@Setting(settingPath = "/com/core/config/elasticsearch/settings.json")
@Mapping(mappingPath = "/com/core/config/elasticsearch/mappings.json")
public class Book {
    @Id
    private Long isbn13;

    private String title;

    private String author;

    private String publisher;

    private String image;

    private String description;

    private String kdc;

    public Book(Map<String, Object> sourceAsMap) {
        this.setIsbn13((Long) sourceAsMap.get("isbn13"));
        this.setTitle(trimField((String) sourceAsMap.get("title")));
        this.setAuthor(trimField((String) sourceAsMap.get("author")));
        this.setPublisher(trimField((String) sourceAsMap.get("publisher")));
        this.setImage(trimField((String) sourceAsMap.get("image")));
        this.setDescription(trimField((String) sourceAsMap.get("description")));
        this.setKdc(trimField((String) sourceAsMap.get("kdc")));
    }

    public static String trimField(String str) {
        if (isNull(str)) {
            return null;
        }

        return str.trim();
    }
}
