package com.core.module.book.web;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.module.book.service.BookService;
import com.core.module.index.dao.Indexing;
import com.core.module.index.vo.IndexVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookRestController {
	private final BookService<?> bookService;
	private final Indexing<?> indexing;

	/**
	 * 초기화 및 새로 인덱싱
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/initUpload")//index가 파일명이랑 
	public ResponseEntity<?> initUpload(IndexVo indexVo) throws IOException{
		indexVo.setIndexName("book");
		String message =bookService.bookInitUpload(indexVo); 
		return ResponseEntity.ok(message);
	}

	/**
	 * 인덱스 초기화
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/initIndex")
	public ResponseEntity<?> initIndex(IndexVo indexVo) throws IOException{
		indexVo.setIndexName("book");
		String message =indexing.initIndex(indexVo.getIndexName()); 
		return ResponseEntity.ok(message);
	}

	/**
	 * 벌크 인덱싱
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/blukUpload")
	public ResponseEntity<?> bulkUpload(IndexVo indexVo) throws IOException{
		indexVo.setIndexName("book");
//		String cntCheckText = indexCntService.findIndexRslt(indexVo);
//		if(!"성공".equals(cntCheckText)) ResponseEntity.ok(cntCheckText);

		String message =bookService.bookUpload(indexVo); 
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
