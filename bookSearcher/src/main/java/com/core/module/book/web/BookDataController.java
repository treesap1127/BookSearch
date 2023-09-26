package com.core.module.book.web;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.Book;
import com.core.module.book.service.BookDataService;
import com.core.module.indexCnt.IndexCntVo;
import com.core.module.indexCnt.service.IndexCntService;
import com.core.module.utils.Indexing;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookDataController {
	private final BookDataService<?> bookDataService;
	private final IndexCntService indexCntService;
	private final Indexing<?> indexing;
	
	/**
	 * 초기화 및 새로 인덱싱
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/initUpload")
	public ResponseEntity<?> initUpload(IndexCntVo indexCntVo) throws IOException{
		indexCntVo.setIndexName("book");
		String message =bookDataService.bookInitUpload(indexCntVo); 
		return ResponseEntity.ok(message);
	}
	
	/**
	 * 인덱스 초기화
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/initIndex")
	public ResponseEntity<?> initIndex(IndexCntVo indexCntVo) throws IOException{
		indexCntVo.setIndexName("book");
		String message =indexing.initIndex(indexCntVo.getIndexName()); 
		return ResponseEntity.ok(message);
	}
	
	/**
	 * 벌크 인덱싱
	 * @param indexCntVo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/blukUpload")
	public ResponseEntity<?> bulkUpload(IndexCntVo indexCntVo) throws IOException{
		indexCntVo.setIndexName("book");
		String cntCheckText = indexCntService.findIndexRslt(indexCntVo);
		if(!"성공".equals(cntCheckText)) ResponseEntity.ok(cntCheckText);
		
		String message =bookDataService.bookUpload(indexCntVo); 
		return ResponseEntity.ok(message);
	}
}
