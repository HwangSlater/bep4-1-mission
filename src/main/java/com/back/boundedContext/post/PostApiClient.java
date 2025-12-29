package com.back.boundedContext.post;

import com.back.shared.post.dto.PostDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostApiClient {
    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/post/api/v1")
            .build();

    public List<PostDto> getItems() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public PostDto getItem(int postId) {
        return restClient.get()
                .uri("/posts/%d".formatted(postId))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}