package com.core.module.book.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.core.module.book.vo.Book;
import org.elasticsearch.action.search.SearchResponse;
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

	/**
	 * 벌크 인덱싱
	 */
	@Override
	public String bookUpload(IndexVo indexVo) throws IOException {
		List<Map<String, Object>> list = BookCsvUpload.ReadCsvFile(indexVo.getExcelFile());
		return indexing.bulkIndexing(indexVo.getIndexName(), list);
	}

	@Override
	public List<Book> search(String keyword) throws IOException {
		SearchResponse searched = indexing.search(keyword);
		List<Book> result = Arrays.stream(searched.getHits().getHits())
				.map(v -> {
					return new Book(v.getSourceAsMap());
				})
				.collect(Collectors.toList());
		return result;
	}
}