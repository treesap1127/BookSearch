package com.core.module.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.dao.BookDataDao;
import com.core.module.book.vo.Book;
import com.core.module.utils.CsvUpload;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookDataServiceImpl implements BookDataService<Book> {
	private final BookDataDao bookDataDao;
	@Override
	public List<Book> bookData() {
		Book bookdata = new Book();
		List<Book> bookdataList = new ArrayList<Book>();
//		bookdata.setSeqNo(6040834L);
//	    bookdata.setIsbnThirteenNo("9791165232092");
//	    bookdata.setVlmNm("2021 ACL 강해준 경찰학 전범위 모의고사 5.0 - 모의고사 총 10회분");
//	    bookdata.setTitleNm("강해준 (지은이)");
//	    bookdata.setAuthrNm("에이씨엘커뮤니케이션");
//	    bookdata.setPrcValue("9000");
//	    bookdata.setImageUrl("https://image.aladin.co.kr/product/27421/48/cover/k482733175_1.jpg");
//	    bookdata.setBookIntrcnCn("최신 개정법령을 반영한 수험서다. 신유형 문제 및 상세한 해설을 수록하였다. 체계적인 구성을 통해 가독성을 향상시켰다.");
//	    bookdata.setPblcteDe("2021-06-28");
	    bookdataList.add(bookdata);
		return bookdataList;
	}
	
    @Override
    public void bookIndexing(String indexName, List<Book> documents, Class<Book> book) {
//    	bookDataDao.bookIndexing(indexName, documents, book);
    }
    
	@Override
	public String bookUpload(MultipartFile excelFile) throws IOException {
		List<Map<String, String>> list = CsvUpload.ReadCsvFile(excelFile);
		
//        List<Map<String, String>> mapList = new ArrayList<>();
//        Map<String, String> bookdata = new HashMap<>();
//        bookdata.put("seqNo", 6040834L);
//        bookdata.put("isbnNo", "9791165232092");
//        bookdata.put("vlmNm", "2021 ACL 강해준 경찰학 전범위 모의고사 5.0 - 모의고사 총 10회분");
//        bookdata.put("titleNm", "강해준 (지은이)");
//        bookdata.put("authrNm", "에이씨엘커뮤니케이션");
//        bookdata.put("price", "9000");
//        bookdata.put("imageUrl", "https://image.aladin.co.kr/product/27421/48/cover/k482733175_1.jpg");
//        bookdata.put("bookIntrcnCn", "최신 개정법령을 반영한 수험서다. 신유형 문제 및 상세한 해설을 수록하였다. 체계적인 구성을 통해 가독성을 향상시켰다.");
//        bookdata.put("pblcteDe", "2021-06-28");
//        mapList.add(bookdata);
//        bookDataDao.bookIndexing("book_index", mapList);
		return bookDataDao.bookIndexing("book", list);
	}
}