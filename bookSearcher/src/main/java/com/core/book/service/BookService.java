package com.core.book.service;

import java.io.IOException;
import java.util.List;

import com.core.book.model.Book;
import com.core.elasitcSearch.model.Index;

public interface BookService<T> {

	List<Book> search(String indexVo) throws IOException;

	String upload(Index index) throws IOException;


}
