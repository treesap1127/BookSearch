package com.core.book.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.book.service.BookService;
import com.core.elasitcSearch.Indexing;
import com.core.elasitcSearch.model.Index;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookRestController {
	private final BookService<?> bookService;
	private final Indexing<?> indexing;

	@PostMapping("/upload")
	public ResponseEntity<?> upload(Index index) throws IOException{
		index.setIndexName("book");
		String message =bookService.upload(index);
		return ResponseEntity.ok(message);
	}

	@PostMapping("/deleteIndex")
	public ResponseEntity<?> initIndex(Index index) throws IOException{
		index.setIndexName("book");
		String message =indexing.deleteIndex(index.getIndexName());
		return ResponseEntity.ok(message);
	}

	@PostMapping("/search")
	public ResponseEntity<?> search(String keyword) throws IOException {
		return ResponseEntity.ok(bookService.search(keyword));
	}
}
