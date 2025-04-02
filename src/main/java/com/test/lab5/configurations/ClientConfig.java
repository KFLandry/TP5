package com.test.lab5.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    @Value("${post.server.url}")
    private String postServerUrl;
    @Bean
    public RestClient postClient() {
        return RestClient.builder()
                .baseUrl(postServerUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean
    public HttpServiceProxyFactory postProxyFactory(RestClient postClientClient) {
        RestClientAdapter adapter = RestClientAdapter.create(postClientClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }
    @Bean
    public PostClient createArenaHumanResourceUniverseClient(HttpServiceProxyFactory postProxyFactory) {
        return postProxyFactory.createClient(PostClient.class);
    }
}
