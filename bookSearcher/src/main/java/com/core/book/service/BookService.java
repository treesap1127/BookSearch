package com.core.book.service;

import java.io.IOException;

import com.core.book.document.Book;
import org.springframework.data.elasticsearch.core.SearchHits;


public interface BookService {

	void upload() throws IOException;

	SearchHits<Book> search(String keyword);
}
