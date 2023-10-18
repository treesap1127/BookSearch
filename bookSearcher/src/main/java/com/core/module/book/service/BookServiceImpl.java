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
import org.elasticsearch.search.profile.ProfileShardResult;
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
	 * 벌크 인덱싱
	 */
	@Override
	public String bookUpload(IndexVo indexVo) throws IOException {
		List<Map<String, Object>> list = BookCsvUpload.ReadCsvFile(indexVo.getExcelFile());
		
		return indexing.bulkIndexing(indexVo.getIndexName(), list);
	}

	@Override
	public SearchResponse search(IndexVo indexVo) throws IOException {
		SearchResponse tes = indexing.search(indexVo);
		Map<String,ProfileShardResult> map= tes.getProfileResults();
		System.out.println("test");
		return indexing.search(indexVo);
    }
}