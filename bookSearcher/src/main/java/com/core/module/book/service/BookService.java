package com.core.module.book.service;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;

import com.core.module.index.vo.IndexVo;

public interface BookService<T> {

	SearchResponse search(IndexVo indexVo) throws IOException;

	String bookUpload(IndexVo indexVo) throws IOException;


}
