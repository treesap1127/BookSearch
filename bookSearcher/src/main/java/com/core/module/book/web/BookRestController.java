package com.core.module.book.web;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.module.book.service.BookService;
import com.core.module.index.IndexVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookRestController {
	private final BookService bookService;
	
	/**
	 * 인덱싱 작업
	 * @param indexVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/index")
	public ResponseEntity<?> Index(IndexVo indexVo) throws IOException{
		indexVo.setIndexName("book");
		
		String message = bookService.Index(indexVo);
		return ResponseEntity.ok(message);
	}
	
	/**
	 * 인덱싱 작업
	 * @param indexVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/deleteIndex")
	public ResponseEntity<?> deleteIndex(IndexVo indexVo) throws IOException{
		indexVo.setIndexName("book");
		
		String message = bookService.deleteIndex(indexVo);
		return ResponseEntity.ok(message);
	}
	
	/**
	 * 검색
	 * @param keyword
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/search")
	public ResponseEntity<?> search(IndexVo indexVo) throws IOException {
		indexVo.setIndexName("book");
		return ResponseEntity.ok(bookService.search(indexVo));
	}
}
