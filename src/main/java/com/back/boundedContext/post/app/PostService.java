package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.global.exception.DomainException;
import com.back.shared.dto.PostDto;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    public Post write(Member author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        eventPublisher.publish(
                new PostCreatedEvent(new PostDto(post))
        );

        return post;
    }

    @Transactional(readOnly = true)
    public Post findById(int id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException("404", "존재하지 않는 post 입니다.")
        );
    }
}
