package com.core.module.book.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookDataDaoImpl<T> implements BookDataDao<T> {
	private final ElasticsearchOperations elasticsearchTemplate;
    private final RestHighLevelClient client;
 
    @Override
	public void bookIndexing(String indexName, List<Map<String, Object>> list) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (Map<String, Object> bookDataMap : list) {
                IndexRequest indexRequest = new IndexRequest(indexName)
                        .id(String.valueOf(bookDataMap.get("seqNo")))
                        .source(bookDataMap, XContentType.JSON);
                bulkRequest.add(indexRequest);
            }

            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
            	System.out.println("Data indexed failures"); 
            } else {
                System.out.println("Data indexed successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void upload() throws IOException {
        String name = "spring";
        CreateIndexRequest request = new CreateIndexRequest(name);

        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );

        client.indices().create(request, RequestOptions.DEFAULT);
    }
}

//    	List<IndexQuery> queries = new ArrayList<>();
//        for (T document : list) {
//            IndexQuery query = new IndexQueryBuilder()
//                    .withObject(document)
//                    .build();
//            queries.add(query);
//        }
//        Class<BookDataVo> BookDataVo;
//		IndexOperations indexOps = elasticsearchTemplate.indexOps(BookDataVo);
//        if (!indexOps.exists()) {
//            indexOps.create();
//            indexOps.putMapping(indexOps.createMapping());
//        }
//        elasticsearchTemplate.bulkIndex(queries, IndexCoordinates.of(indexName));
//	    }
//	@Override
//	public void bookIndexing(String indexNm, List<T> documents , Class<T> tClass ) {
//        List<IndexQuery> queries = new ArrayList<>();
//        for (T document : documents) {
//            IndexQuery query = new IndexQueryBuilder()
//                    .withObject(document)
//                    .build();
//            queries.add(query);
//        }
//        IndexOperations indexOps = elasticsearchTemplate.indexOps(tClass);
//        if (!indexOps.exists()) {
//            indexOps.create();
//            indexOps.putMapping(indexOps.createMapping());
//        }
//        elasticsearchTemplate.bulkIndex(queries, IndexCoordinates.of(indexNm));
//    }
//	    }
