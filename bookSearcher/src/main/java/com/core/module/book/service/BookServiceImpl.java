package com.core.module.book.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.core.module.index.dao.Indexing;
import com.core.module.index.vo.IndexVo;
import com.core.utils.BookCsvUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final Indexing<?> indexing;
//	private final IndexCntService indexCntService;
    
	/**
	 * 인덱스 초기화 후 벌크 인덱싱
	 */
	@Override
	public String bookInitUpload(IndexVo indexVo) throws IOException {
		List<Map<String, Object>> list = BookCsvUpload.ReadCsvFile(indexVo.getExcelFile());
		
//		if (list.size()>=50000000) {
//			log.info("인덱스가 많습니다");
//			return "인덱싱 할 데이터의 양이 많아 실패하였습니다.";
//		}
		indexing.InitIndexing(indexVo.getIndexName(), list);

//		indexCntService.deleteIndexCnt(indexCntVo);
		
//		indexVo.setIndexCnt(list.size());
//		indexCntService.createIndexCnt(indexCntVo);
		
		return "인덱싱 성공";
	}

	/**
	 * 인덱스 확인 후 벌크 인덱싱
	 */
	@Override
	public String bookUpload(IndexVo indexVo) throws IOException {
		List<Map<String, Object>> list = BookCsvUpload.ReadCsvFile(indexVo.getExcelFile());
		
//		IndexVo indexVo = indexCntService.findIndexCnt(indexVo);
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
		indexing.bulkIndexing(indexVo.getIndexName(), list);

//		indexVo.setIndexCnt(list.size());
//		indexCntService.createIndexCnt(indexCntVo);
		
		return "인덱싱 성공";
	}


	@Override
	public SearchResponse search(IndexVo indexVo) throws IOException {
		return indexing.search(indexVo);
    }
}