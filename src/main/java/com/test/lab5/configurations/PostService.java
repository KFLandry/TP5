package com.test.lab5.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostClient postClient;

    public String register(Post post) {
        return postClient.register(post);
    }
}
