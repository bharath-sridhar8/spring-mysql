package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    private final RestTemplate restTemplate;

    public AsyncService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<GithubUser> getUser(String user) {
        GithubUser forObject = restTemplate.getForObject(String.format("https://api.github.com/users/%s", user), GithubUser.class);
        return CompletableFuture.completedFuture(forObject);
    }
}
