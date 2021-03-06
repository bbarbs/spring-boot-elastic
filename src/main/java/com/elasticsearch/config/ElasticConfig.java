package com.elasticsearch.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticConfig {

    /**
     * It uses transport client.
     * see: https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
     *
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public Client client() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}
