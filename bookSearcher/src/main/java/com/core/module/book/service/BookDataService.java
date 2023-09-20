package com.core.module.book.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.vo.Book;

public interface BookDataService<T> {

	List<Book> bookData();

	String bookUpload(MultipartFile excelFile) throws IOException;

   
}
