package com.core.module.book.service;

import java.io.IOException;
import java.util.List;
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

import com.core.module.book.document.Book;
import com.core.module.index.IndexVo;
import com.core.module.index.dao.IndexDao;
import com.core.utils.BookCsvUpload;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	private final IndexDao indexDao;
    
	/**
	 * 인덱스 초기화 후 벌크 인덱싱
	 * @throws IOException 
	 */
	@Override
	public String Index(IndexVo indexVo) throws IOException  {
		List<IndexQuery> indexQueries = BookCsvUpload.ReadCsvFile(indexVo.getExcelFile());

		indexDao.bulkIndex(indexQueries, indexVo.getIndexName());
		
		return "인덱싱 성공";
	}

	@Override
	public SearchResponse search(IndexVo indexVo) throws IOException {
		return indexDao.search(indexVo);
    }

}