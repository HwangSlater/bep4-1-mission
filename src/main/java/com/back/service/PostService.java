package com.back.service;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post join(Member author, String title, String content) {
        return postRepository.save(new Post(author, title, content));
    }
}
