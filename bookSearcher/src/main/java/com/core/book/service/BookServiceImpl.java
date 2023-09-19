package com.core.book.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.core.book.document.Book;
import com.core.utils.CSVReader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final CSVReader csvReader;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;
	private final RestHighLevelClient elasticsearchClient;

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

	@Override
	public SearchResponse search(String keyword) throws IOException {
//	TODO: 2개 이상의 개념 검색 -> 개념들의 융합에 부합하는 검색 결과 도출 필요
		SearchRequest searchRequest = new SearchRequest("book");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("title", keyword).boost(3))
				.should(QueryBuilders.matchQuery("subInfoText", keyword).boost(1))
				.should(QueryBuilders.matchQuery("description", keyword).boost(2));

		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.size(10);

		searchRequest.source(sourceBuilder);

		return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}