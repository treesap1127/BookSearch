package com.core.module.book.service;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;

import com.core.module.index.IndexVo;

public interface BookService {

	SearchResponse search(IndexVo indexVo) throws IOException;

	String Index(IndexVo indexVo) throws IOException;


}
