package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class DataSupplierForStub {

    private final WebClient client;
    private final String stubUrl;
    private final String login;
    private final String password;

    public DataSupplierForStub(@Value("${stub.url}") String stubUrl,
                               @Value("${stub.login}") String login,
                               @Value("${stub.password}")String password) {
        this.stubUrl = stubUrl;
        this.login = login;
        this.password = password;

        this.client = initWebClient();
    }

    private WebClient initWebClient() {
        log.debug("Add auth header. login = {}, password = {}", login, password);
        return WebClient.builder()
                .baseUrl(stubUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(login, password))
                .build();
    }


    public String executeRequest(String uri) {
        return client
                .get().uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
