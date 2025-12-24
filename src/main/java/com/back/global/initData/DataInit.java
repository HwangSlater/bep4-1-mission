package com.back.global.initData;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
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
    private final MemberFacade memberFacade;
    private final PostFacade postFacade;

    public DataInit(@Lazy DataInit self, MemberFacade memberJoinUseCase, PostFacade postFacade) {
        this.self = self;
        this.memberFacade = memberJoinUseCase;
        this.postFacade = postFacade;
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
        if (memberFacade.count() > 0) return;

        Member systemMember = memberFacade.join("system", "1234", "시스템").getData();
        Member holdingMember = memberFacade.join("holding", "1234", "홀딩").getData();
        Member adminMember = memberFacade.join("admin", "1234", "관리자").getData();
        Member user1Member = memberFacade.join("user1", "1234", "유저1").getData();
        Member user2Member = memberFacade.join("user2", "1234", "유저2").getData();
        Member user3Member = memberFacade.join("user3", "1234", "유저3").getData();
    }

    @Transactional
    public void makeBasePosts() {
        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        if(postFacade.count() > 0) return;

        IntStream.range(0, 3).forEach(i -> {
            postFacade.write(user1Member, "user1Member - testPost" + i, "testContent" + i);

            if(i<2) {
                postFacade.write(user2Member, "user2Member - testPost" + i, "testContent" + i);
            }
        });

        postFacade.write(user3Member, "user3Member - testPost", "testContent");
    }


    @Transactional
    public void makeBasePostComment() {
        Member user1Member = memberFacade.findByUsername("user1").get();
        Member user2Member = memberFacade.findByUsername("user2").get();
        Member user3Member = memberFacade.findByUsername("user3").get();

        Post post1 = postFacade.findById(1);
        Post post2 = postFacade.findById(2);
        Post post3 = postFacade.findById(3);
        Post post4 = postFacade.findById(4);

        if(!post1.hasComments()) {
            post1.addComment(user1Member, "댓글1");
            post1.addComment(user2Member, "댓글2");
            post1.addComment(user3Member, "댓글3");
        }

        if(!post2.hasComments()) {
            post2.addComment(user2Member, "댓글4");
            post2.addComment(user2Member, "댓글5");
        }

        if(!post3.hasComments()) {
            post3.addComment(user3Member, "댓글6");
            post3.addComment(user1Member, "댓글7");
        }

        if(!post4.hasComments()) {
            post4.addComment(user1Member, "댓글8");
        }

    }
}