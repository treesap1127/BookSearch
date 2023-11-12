package com.core.book.service;

import java.io.IOException;
import java.util.List;

import com.core.book.model.Book;
import com.core.elasitcSearch.model.IndexVo;
import org.elasticsearch.search.SearchHit;

public interface BookService<T> {

	List<Book> search(String indexVo) throws IOException;

	String bookUpload(IndexVo indexVo) throws IOException;


	List<SearchHit[]> searchTest(String keyword) throws IOException;
}