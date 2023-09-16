package com.core.module.book.dao;

import java.util.List;
import java.util.Map;

import com.core.module.book.vo.Book;
import com.core.module.book.vo.BookDocVo;


public interface BookDataDao<T> {

//	void bookIndexing(String indexNm, List<T> documents, Class<T> book);

	String bookIndexing(String string, List<Map<String, String>> mapList);


}
