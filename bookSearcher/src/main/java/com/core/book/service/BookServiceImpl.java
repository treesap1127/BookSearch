package com.core.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.core.book.document.Book;
import com.core.utils.CSVReader;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.book.dao.BookDataDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final CSVReader csvReader;

    @Override
    public void upload() throws IOException {
		List<Book> list = csvReader.readFile("/Users/jihunjang/Downloads/도서 데이터/202111-v4.csv");

		List<IndexQuery> indexQueries = list.stream()
				.map(book -> new IndexQueryBuilder()
						.withId(book.getId().toString())
						.withObject(book)
						.build())
				.collect(Collectors.toList());

		List<IndexedObjectInformation> result = elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of("book"));
		System.out.println(result);
	}
}