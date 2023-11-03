package com.core.book.model;


import lombok.Data;

import java.util.Map;

import static java.util.Objects.isNull;

@Data
public class Book {
	
    private Long isbn;

    private String title;

    private String author;

    private String publisher;

    private String image;

    private String description;

    private Double kdc;

    public Book(Map<String, Object> sourceAsMap) {
        this.setIsbn((Long) sourceAsMap.get("isbn13"));
        this.setTitle(trimField((String) sourceAsMap.get("title")));
        this.setAuthor(trimField((String) sourceAsMap.get("author")));
        this.setPublisher(trimField((String) sourceAsMap.get("publisher")));
        this.setImage(trimField((String) sourceAsMap.get("image")));
        this.setDescription(trimField((String) sourceAsMap.get("description")));
        String kdc = trimField((String) sourceAsMap.get("kdc"));
        if (!kdc.isEmpty()) {
            this.setKdc(Double.parseDouble(kdc));
        }
    }

    public static String trimField(String str) {
        if (isNull(str)) {
            return null;
        }

        return str.trim();
    }
}