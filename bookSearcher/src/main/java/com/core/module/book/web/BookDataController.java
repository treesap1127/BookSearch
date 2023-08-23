package com.core.module.book.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.service.BookDataService;
import com.core.module.book.vo.BookDataVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookDataController {
	private final BookDataService bookDataService;
	
	@RequestMapping("/indexing")
	public <T> void bookData() {
		System.out.println("DataSet Start");
		List<BookDataVo> bookDataList=bookDataService.bookData();
		try {
		    Class<?> bookDataClass = Class.forName("com.core.module.book.vo.BookDataVo");
			System.out.println("DataSet Complete");

			System.out.println("indexing Start");
			bookDataService.bookIndexing("2009",bookDataList, bookDataClass);
			System.out.println("indexing Complete");
			
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestBody MultipartFile excelFile) throws IOException{
		String message = "fileUpload Complete";
		bookDataService.bookUpload(excelFile);
		
		return ResponseEntity.ok(message);
	}


	@PostMapping("/uploadTest")
	public ResponseEntity<?> uploadTest(@RequestBody MultipartFile excel) throws IOException{
		bookDataService.upload(excel);

		return ResponseEntity.ok("fileUpload Complete");
	}
}
