package com.core.elasitcSearch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;


public interface Indexing<T> {
	
	String bulkIndexing(String indexName, List<Map<String, Object>> list);

	String deleteIndex(String indexName);

	Map<String, SearchResponse> search(String keyword) throws IOException;
}
