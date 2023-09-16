package com.core.book.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.core.book.document.Book;
import com.core.utils.CSVReader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final CSVReader csvReader;
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

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
	public SearchHits<Book> search(String keyword) {

    	//알림 테스트
//		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//				.withQuery(QueryBuilders.functionScoreQuery(
//						QueryBuilders.boolQuery()
//								.should(QueryBuilders.matchQuery("title", keyword))
//								.should(QueryBuilders.matchQuery("subInfoText", keyword))
//								.should(QueryBuilders.matchQuery("description", keyword)),
//						ScoreFunctionBuilders.weightFactorFunction("title", 3)
//								.weightFactorFunction("subInfoText", 2)
//								.weightFactorFunction("description", 1))
//						.build());
//
//		return elasticsearchRestTemplate.search(searchQuery, Book.class);


//		SearchHits hits = searchResponse.getHits();
//		return hits.stream().map(hit -> {
//			Book book = new Book();
//			book.setId(hit.getId());
//			book.setTitle(hit.getSourceAsMap().get("title").toString());
//			book.setSubInfoText(hit.getSourceAsMap().get("subInfoText").toString());
//			book.setDescription(hit.getSourceAsMap().get("description").toString());
//			return book;
//		}).collect(Collectors.toList());


//		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//		NativeSearchQuery query = builder.withQuery(QueryBuilders.functionScoreQuery()
//				.add(QueryBuilders.matchQuery("name", keyword), ScoreFunctionBuilders.weightFactorFunction(8))
//				.add(QueryBuilders.matchQuery("subTitle", keyword), ScoreFunctionBuilders.weightFactorFunction(2))
//				.add(QueryBuilders.matchQuery("keywords", keyword), ScoreFunctionBuilders.weightFactorFunction(2))
//				.scoreMode("sum"))
//				.build();
//
//		return elasticsearchRestTemplate.search(query, Book.class);
	return null;
    }
}