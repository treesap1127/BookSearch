package com.core.book.service;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;


public interface BookService {

	void upload() throws IOException;

	SearchResponse search(String keyword) throws IOException;
}
