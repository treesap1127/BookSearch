package com.core.module.book.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.vo.BookDataVo;

public interface BookDataService<T> {

	List<BookDataVo> bookData();

    void bookIndexing(String indexName, List<T> documents, Class<T> bookDataVo);

	void bookUpload(MultipartFile excelFile) throws IOException;

   
}
