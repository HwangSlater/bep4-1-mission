package com.back.initData;

import com.back.entity.Member;
import com.back.service.MemberService;
import com.back.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;

    public DataInit(@Lazy DataInit self, MemberService memberService, PostService postService) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberService.count() > 0) return;

        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");
    }

    public void makeBasePosts() {
        Member user1Member = memberService.findByUsername("user1");
        Member user2Member = memberService.findByUsername("user2");
        Member user3Member = memberService.findByUsername("user3");

        if(postService.count() > 0) return;

        IntStream.range(0, 3).forEach(i -> {
            postService.join(user1Member, "user1Member - testPost" + i, "testContent" + i);

            if(i<2) {
                postService.join(user2Member, "user2Member - testPost" + i, "testContent" + i);
            }
        });

        postService.join(user3Member, "user3Member - testPost", "testContent");
    }
}