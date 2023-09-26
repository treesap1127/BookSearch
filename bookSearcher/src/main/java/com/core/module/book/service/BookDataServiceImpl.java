package com.core.module.book.service;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.core.module.book.Book;
import com.core.module.indexCnt.IndexCntVo;
import com.core.module.indexCnt.service.IndexCntService;
import com.core.module.utils.BookCsvUpload;
import com.core.module.utils.Indexing;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@RequiredArgsConstructor
public class BookDataServiceImpl implements BookDataService<Book> {
	private final Indexing<?> indexing;
	private final IndexCntService indexCntService;
    
	/**
	 * 인덱스 초기화 후 벌크 인덱싱
	 */
	@Override
	public String bookInitUpload(IndexCntVo indexCntVo) throws IOException {
		List<Map<String, String>> list = BookCsvUpload.ReadCsvFile(indexCntVo.getExcelFile());
		
//		if (list.size()>=50000000) {
//			log.info("인덱스가 많습니다");
//			return "인덱싱 할 데이터의 양이 많아 실패하였습니다.";
//		}
		indexing.InitIndexing(indexCntVo.getIndexName(), list);

//		indexCntService.deleteIndexCnt(indexCntVo);
		
		indexCntVo.setIndexCnt(list.size());
//		indexCntService.createIndexCnt(indexCntVo);
		
		return "인덱싱 성공";
	}

	/**
	 * 인덱스 확인 후 벌크 인덱싱
	 */
	@Override
	public String bookUpload(IndexCntVo indexCntVo) throws IOException {
		List<Map<String, String>> list = BookCsvUpload.ReadCsvFile(indexCntVo.getExcelFile());
		
		IndexCntVo indexVo = indexCntService.findIndexCnt(indexCntVo);
		if (indexVo == null || "".equals(indexVo.getIndexName()) ) {
			log.info("인덱스가 없습니다");
			return "인덱스가 존재하지 않습니다.";
		}
		
//		int indexCnt = indexVo.getIndexCnt() + list.size() ;
//
//		if (indexCnt>=50000000) {
//			log.info("인덱스가 많습니다");
//			return "인덱싱 할 데이터의 양이 많아 실패하였습니다.";
//		}
		indexing.bulkIndexing(indexCntVo.getIndexName(), list);

		indexCntVo.setIndexCnt(list.size());
//		indexCntService.createIndexCnt(indexCntVo);
		
		return "인덱싱 성공";
	}

}