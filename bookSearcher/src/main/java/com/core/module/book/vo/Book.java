package com.core.module.book.vo;


import lombok.Data;

@Data
public class Book {
	
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