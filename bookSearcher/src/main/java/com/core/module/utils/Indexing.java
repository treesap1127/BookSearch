package com.core.module.utils;

import java.util.List;
import java.util.Map;

import com.core.module.indexCnt.IndexCntVo;

public interface Indexing<T> {
	String InitIndexing(String string, List<Map<String, String>> list);

	String initIndex(String indexName);

	String bulkIndexing(String indexName, List<Map<String, String>> list);
}
