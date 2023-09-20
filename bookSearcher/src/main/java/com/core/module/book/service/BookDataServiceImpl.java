package com.core.module.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.dao.BookDataDao;
import com.core.module.book.vo.Book;
import com.core.module.utils.BookCsvUpload;
import com.core.module.utils.Indexing;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookDataServiceImpl implements BookDataService<Book> {
	private final Indexing indexing;
	private final BookDataDao bookDataDao;
	@Override
	public List<Book> bookData() {
		Book bookdata = new Book();
		List<Book> bookdataList = new ArrayList<Book>();

		bookdataList.add(bookdata);
		return bookdataList;
	}
    
	@Override
	public String bookUpload(MultipartFile excelFile) throws IOException {
		List<Map<String, String>> list = BookCsvUpload.ReadCsvFile(excelFile);
		
		indexing.Indexing("book", list);
		return "성공";
	}
}