package com.core.module.index.dao;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Repository;

import com.core.module.index.IndexVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class IndexDaoImpl implements IndexDao {
    private final ElasticsearchOperations elasticsearchOperations;
    private final RestHighLevelClient elasticsearchClient;

	@Override
	public void bulkIndex(List<IndexQuery> indexQueries, String indexNm) {
        elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of(indexNm));
	}
	
	@Override
	public void deleteIndex(IndexVo indexVo) {
		elasticsearchOperations.indexOps(IndexCoordinates.of(indexVo.getIndexName())).delete();
	}
	
	@Override
	public SearchResponse search(IndexVo indexVo) throws IOException {
		//	TODO: 2개 이상의 개념 검색 -> 개념들의 융합에 부합하는 검색 결과 도출 필요
		SearchRequest searchRequest = new SearchRequest(indexVo.getIndexName());
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("title", indexVo.getKeyword()).boost(1))
				.should(QueryBuilders.matchQuery("description", indexVo.getKeyword()).boost(2))
				.should(QueryBuilders.matchQuery("image", indexVo.getKeyword()).boost(3))
				.should(QueryBuilders.matchQuery("author", indexVo.getKeyword()).boost(4))
				.should(QueryBuilders.matchQuery("publisher", indexVo.getKeyword()).boost(5))
				.should(QueryBuilders.matchQuery("isbn", indexVo.getKeyword()).boost(6));

		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.size(10);

		searchRequest.source(sourceBuilder);
		return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
	}
	
	private int calculateDelay(int retry) {
        return (int) (Math.pow(2, retry) * 100); // 0.2초 딜레이
    }
	
}
