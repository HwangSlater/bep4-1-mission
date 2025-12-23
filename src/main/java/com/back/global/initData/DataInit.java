package com.back.global.initData;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.member.service.MemberService;
import com.back.boundedContext.post.service.PostService;
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
            self.makeBasePostComment();
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

    @Transactional
    public void makeBasePosts() {
        Member user1Member = memberService.findByUsername("user1").get();
        Member user2Member = memberService.findByUsername("user2").get();
        Member user3Member = memberService.findByUsername("user3").get();

        if(postService.count() > 0) return;

        IntStream.range(0, 3).forEach(i -> {
            postService.write(user1Member, "user1Member - testPost" + i, "testContent" + i);

            if(i<2) {
                postService.write(user2Member, "user2Member - testPost" + i, "testContent" + i);
            }
        });

        postService.write(user3Member, "user3Member - testPost", "testContent");
    }


    @Transactional
    public void makeBasePostComment() {
        Member user1Member = memberService.findByUsername("user1").get();
        Member user2Member = memberService.findByUsername("user2").get();
        Member user3Member = memberService.findByUsername("user3").get();

        Post post1 = postService.findById(1);
        Post post2 = postService.findById(2);
        Post post3 = postService.findById(3);
        Post post4 = postService.findById(4);

        if(!post1.hasComments()) {
            postService.addComment(post1, user1Member, "댓글1");
            postService.addComment(post1, user2Member, "댓글2");
            postService.addComment(post1, user3Member, "댓글3");
        }

        if(!post2.hasComments()) {
            postService.addComment(post2, user2Member, "댓글4");
            postService.addComment(post2, user2Member, "댓글5");
        }

        if(!post3.hasComments()) {
            postService.addComment(post3, user3Member, "댓글6");
            postService.addComment(post3, user1Member, "댓글7");
        }

        if(!post4.hasComments()) {
            postService.addComment(post4, user1Member, "댓글8");
        }

    }
}