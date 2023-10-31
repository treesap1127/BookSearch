package com.core.module.index.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;

import com.core.module.index.vo.IndexVo;


public interface Indexing<T> {
	
	String bulkIndexing(String indexName, List<Map<String, Object>> list);

	String deleteIndex(String indexName);

	SearchResponse search(String keyword) throws IOException;
}
