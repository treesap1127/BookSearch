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
import com.core.module.book.vo.Book;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookDataController {
	private final BookDataService bookDataService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestBody MultipartFile excelFile) throws IOException{
		String message =bookDataService.bookUpload(excelFile); 

		return ResponseEntity.ok(message);

	}
}
