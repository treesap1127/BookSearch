package com.core.module.index.dao;

import static java.util.Objects.nonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.StopWatch;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.core.config.ElasticSearchConfig;
import com.core.module.index.IndexEnum;
import com.core.module.index.vo.IndexVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class IndexingImpl<T> implements Indexing<T> {
	private final RestHighLevelClient elasticsearchClient;
    private final ElasticSearchConfig config;
//	private final IndexCntService indexCntService;


	/**
     * 인덱싱 초기화 후 데이터 삽입
     */
    public String InitIndexing(String indexName, List<Map<String, Object>> list) {
        RestHighLevelClient client = config.createConnection();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if (list == null || list.isEmpty()) {
            log.error("Empty Data");
            return "데이터가 존재 하지 않습니다.";
        }
        if(isExistIndex(indexName)) {
        	if(!deleteIndex(indexName)) {
                log.info(indexName + "fail to delete index");
                return "인덱스 삭제에 실패하였습니다.";
            }
        }
        boolean acknowledged = createIndex(indexName, client);

        if (acknowledged) {
            log.info(indexName + " success to create index");
        } else {
            log.info(indexName + " fail to create index");
            return "인덱스 생성을 실패하였습니다.";
        }
        try{
        	bulkIndexing(indexName, list, client);	
        } catch(Exception e) {
        	log.info("인덱싱에 실패하였습니다.");
        	return "인덱싱에 실패하였습니다.";
        }

        config.closeConnection(client);
        stopWatch.stop();
        log.info("인덱싱에 완료했습니다.");
		return "인덱싱을 완료했습니다.";
    }
    
    /**
     * 인덱스 초기화
     */
	@Override
	public String initIndex(String indexName) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if(isExistIndex(indexName)) {
        	if(!deleteIndex(indexName)) {
                log.info(indexName + "fail to delete index");
                return "인덱스 삭제에 실패하였습니다.";
            }
        }
        stopWatch.stop();
        log.info("인덱싱에 삭제에 성공하였습니다.");
		return "인덱스 삭제에 성공하였습니다.";
	}
	
	/**
	 * 벌크 인덱싱
	 */
	@Override
	public String bulkIndexing(String indexName, List<Map<String, Object>> list) {
		RestHighLevelClient client = config.createConnection();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if (list == null || list.isEmpty()) {
            log.error("Empty Data");
            return "데이터가 존재 하지 않습니다.";
        }
        
        try{
        	bulkIndexing(indexName, list, client);	
        } catch(Exception e) {
        	log.info("인덱싱에 실패하였습니다");
        	return "인덱싱에 실패하였습니다.";
        }

        config.closeConnection(client);
        stopWatch.stop();
        log.info("인덱싱에 완료했습니다");
		return "인덱싱을 완료했습니다.";
	}

    
    /**
	 * 엘라스틱서치 인덱스 존재여부 확인
	 * @param indexName 인덱스 이름
	 * @return 인덱스 존재 여부
	 */
	private boolean isExistIndex(String indexName) {
	    RestHighLevelClient client = null;
	    boolean acknowledged = false;
	    try {
	        client = config.createConnection();
	        GetIndexRequest request = new GetIndexRequest(indexName);
	        acknowledged = client.indices().exists(request, RequestOptions.DEFAULT);
	    } catch (Exception e) {
	        log.error(e.getMessage());
	    } finally {
	        config.closeConnection(client);
	    }
	
	    return acknowledged;
	}

    /**
     * 인덱스 생성
     * @param indexName
     * @param client
     * @return
     */
	private boolean createIndex(String indexName, RestHighLevelClient client) {
		String doc = getDocuments(indexName,"all");
		
        CreateIndexRequest request = new CreateIndexRequest(indexName);

		request.source(doc, XContentType.JSON);

        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            return createIndexResponse.isAcknowledged();
        } catch (IOException e) {
            log.error(e.toString());
            return false;
        }
    }
	
	/**
	 * 인덱스 이름으로 도큐먼트 조회
	 * @param indexName
	 * @param type 
	 * @return
	 */
    private String getDocuments(String indexName, String type) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            InputStream in = this.getClass().getResourceAsStream(IndexEnum.findPath(indexName,type));
            byte[] buffer = new byte[1024];
            int length;
            if(nonNull(in)) {
                while ((length = in.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
            }
        } catch(Exception e) {
            log.error("Not found documents by indexName : " + indexName);
        }

        return result.toString();
    }


	/**
        * 인덱스 삭제
        */
       public boolean deleteIndex(String indexName) {
           RestHighLevelClient client = null;
           boolean acknowledged = false;
           try {
               client = config.createConnection();
               DeleteIndexRequest request = new DeleteIndexRequest(indexName);
               AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
               acknowledged = response.isAcknowledged();
           } catch (ElasticsearchException | IOException e) {
               log.error(e.toString());
           } finally {
               config.closeConnection(client);
           }

           if(acknowledged) {
               log.info(indexName + "success to delete index");
           } else {
               log.info(indexName + "fail to delete index");
           }

           return acknowledged;
       }

    /**
     * 인덱싱 데이터 삽입
     * @param indexName
     * @param indexableData
     * @param client
     */
	private void bulkIndexing(
            String indexName,
            List<Map<String, Object>> indexableData,
            RestHighLevelClient client
    ) {
        BulkRequest bulkRequest = new BulkRequest();

        for (Map<String, Object> el : indexableData) {
            bulkRequest.add(new IndexRequest(indexName).source(el, XContentType.JSON));
        }

        try {
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                if (bulkItemResponse.isFailed()) {
                    BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                    System.out.println(failure.getIndex() + " - " + failure.getType() + " - " + failure.getId() + " / "
                            + failure.getMessage());
                }
            }
        } catch (IOException e) {
        	log.info("인덱싱에 실패하였습니다."+e.getMessage());
        }
    }

	
	@Override
	public SearchResponse search(IndexVo indexVo) throws IOException {
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
}

