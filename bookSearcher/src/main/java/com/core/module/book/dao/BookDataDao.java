package com.core.module.book.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.core.module.book.vo.BookDataVo;


public interface BookDataDao<T> {

//	void bookIndexing(String indexNm, List<T> documents, Class<T> bookDataVo);

	void bookIndexing(String string, List<Map<String, Object>> list);


	void upload() throws IOException;

}
