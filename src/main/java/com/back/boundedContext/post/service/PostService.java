package com.back.boundedContext.post.service;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.global.exception.DomainException;
import com.back.boundedContext.post.repository.PostRepository;
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

    public Post write(Member author, String title, String content) {
        author.addActionScore(3);

        return postRepository.save(new Post(author, title, content));
    }

    public Post findById(int id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException("404", "존재하지 않는 post 입니다.")
        );
    }
}
