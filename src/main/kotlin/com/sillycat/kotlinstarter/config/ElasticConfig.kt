package com.sillycat.kotlinstarter.config

import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import com.sillycat.kotlinstarter.config.ElasticEnvParams
import org.apache.http.HttpHost

@Configuration
class ElasticConfig : AbstractElasticsearchConfiguration(){

//    @Bean
//    override fun elasticsearchClient(): RestHighLevelClient {
//        val host = ElasticEnvParams.ELASTIC_HOST.get()
//        val port = ElasticEnvParams.ELASTIC_PORT.get().toInt()
//        val user = ElasticEnvParams.ELASTIC_USERNAME.get()
//        val password = ElasticEnvParams.ELASTIC_PASSWORD.get()
//
//        val credentialsProvider = BasicCredentialsProvider()
//        credentialsProvider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(user, password))
//
//        val restClientBuilder = RestClient.builder(
//            HttpHost(host, port, "https")
//        ).setHttpClientConfigCallback { httpClientBuilder: HttpAsyncClientBuilder ->
//            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
//        }
//        return RestHighLevelClient(restClientBuilder)
//    }

    @Bean
    override fun elasticsearchClient(): RestHighLevelClient {
        val host = ElasticEnvParams.ELASTIC_HOST.get()
        val port = ElasticEnvParams.ELASTIC_PORT.get().toInt()
        val user = ElasticEnvParams.ELASTIC_USERNAME.get()
        val password = ElasticEnvParams.ELASTIC_PASSWORD.get()

        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(user, password))

        val restClientBuilder = RestClient.builder(
            HttpHost(host, port)
        ).setHttpClientConfigCallback { httpClientBuilder: HttpAsyncClientBuilder ->
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
        }
        return RestHighLevelClient(restClientBuilder)
    }

}