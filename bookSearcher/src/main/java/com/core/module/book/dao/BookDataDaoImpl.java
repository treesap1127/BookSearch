package com.core.module.book.dao;

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
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.StopWatch;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.core.config.ElasticSearchConfig;
import com.core.module.search.Index;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookDataDaoImpl<T> implements BookDataDao<T> {
    private final ElasticSearchConfig config;
//    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * 도서 목록 인덱싱
     */
    @Override
    public String bookIndexing(String indexName, List<Map<String, String>> list) {
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
        }

        bulkIndexing(indexName, list, client);

        config.closeConnection(client);
        stopWatch.stop();
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
//		String mappingdoc = getDocuments(indexName,"mapping");
//		String Settingdoc = getDocuments(indexName,"setting");
		String doc = getDocuments(indexName,"all");
//		
//		CreateIndexRequest request = new CreateIndexRequest(indexName);
//
//
//		try {
//			CreateIndexResponse createIndexResponse =client.indices().create(request, RequestOptions.DEFAULT);
//			return createIndexResponse.isAcknowledged();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		
        CreateIndexRequest request = new CreateIndexRequest(indexName);

//        request.mapping(mappingdoc, XContentType.JSON);
//		request.settings(Settingdoc, XContentType.JSON);
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
            InputStream in = this.getClass().getResourceAsStream(Index.findPath(indexName,type));
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
            List<Map<String, String>> indexableData,
            RestHighLevelClient client
    ) {
//		List<IndexQuery> indexQueries = indexableData.stream()
//			    .map(data -> {
//			        String id = data.get("id");
//			        return new IndexQueryBuilder()
//			            .withId(id)
//			            .withObject(data)
//			            .build();
//			    })
//			    .collect(Collectors.toList());
//
//			List<IndexedObjectInformation> result = elasticsearchOperations.bulkIndex(indexQueries, IndexCoordinates.of("book"));
//	}

        BulkRequest bulkRequest = new BulkRequest();

        for (Map<String, String> el : indexableData) {
            bulkRequest.add(new IndexRequest(indexName).source(el, XContentType.JSON));
        }

        try {
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                if (bulkItemResponse.isFailed()) {
                    BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                    log.info(failure.getIndex() + " - " + failure.getType() + " - " + failure.getId() + " / "
                            + failure.getMessage());
                }
            }
        } catch (IOException e) {
            log.error("index error," + e.getMessage());
        }
    }
}

