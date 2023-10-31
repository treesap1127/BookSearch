package com.core.module.book.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.core.module.book.vo.Book;
import com.core.module.index.vo.IndexVo;
import org.elasticsearch.search.profile.ProfileShardResult;

public interface BookService<T> {

	List<Book> search(String indexVo) throws IOException;

	String bookUpload(IndexVo indexVo) throws IOException;


}
