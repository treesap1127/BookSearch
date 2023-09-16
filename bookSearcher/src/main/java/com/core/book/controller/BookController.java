package com.core.book.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.book.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
	private final BookService bookService;

	@PostMapping("/uploadTest")
	public ResponseEntity<?> uploadTest(@RequestBody MultipartFile excel) throws IOException{
		bookService.upload();
		return ResponseEntity.ok("fileUpload Complete");
	}

	@PostMapping("/search")
	public ResponseEntity<?> search(String keyword){
		return ResponseEntity.ok(bookService.search(keyword));
	}
}
