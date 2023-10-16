package com.core.module.index.dao;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import com.core.module.index.IndexVo;

public interface IndexDao {

	void bulkIndex(List<IndexQuery> indexQueries,String indexNm);

	SearchResponse search(IndexVo indexVo) throws IOException;

	void deleteIndex(IndexVo indexVo);

}
