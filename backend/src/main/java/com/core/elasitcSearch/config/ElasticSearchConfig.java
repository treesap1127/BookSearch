package com.core.elasitcSearch.config;

import static java.lang.Integer.parseInt;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ElasticSearchConfig{

    @Value("${elasticsearch.ip}")
    private String ip;
    @Value("${elasticsearch.port}")
    private String port;
    @Value("${elasticsearch.protocol}")
    private String protocol;

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 600000;

    /**
     * 연결
     * @return
     */
    public RestHighLevelClient createConnection() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(ip, parseInt(port), protocol))
                .setRequestConfigCallback(
                        requestConfigBuilder -> requestConfigBuilder
                                .setConnectTimeout(CONNECT_TIMEOUT)
                                .setSocketTimeout(SOCKET_TIMEOUT));

        return new RestHighLevelClient(builder);
    }

    /**
     * 해제
     * @param client
     */
    public void closeConnection(RestHighLevelClient client) {
        try {
            if(client != null) {
                client.close();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}