package com.back.boundedContext.post.in;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
public class PostDataInit {
    private final PostDataInit self;
    private final MemberFacade memberFacade;
    private final PostFacade postFacade;

    public PostDataInit(@Lazy PostDataInit self, MemberFacade memberJoinUseCase, PostFacade postFacade) {
        this.self = self;
        this.memberFacade = memberJoinUseCase;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postInitDataRunner() {
        return args -> {
            self.makeBasePosts();
            self.makeBasePostComment();
        };
    }

    @Transactional
    public void makeBasePosts() {
        PostMember user1Member = postFacade.findMemberByUsername("user1").get();
        PostMember user2Member = postFacade.findMemberByUsername("user2").get();
        PostMember user3Member = postFacade.findMemberByUsername("user3").get();

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

        Post post1 = postFacade.findPostById(1).get();
        Post post2 = postFacade.findPostById(2).get();
        Post post3 = postFacade.findPostById(3).get();
        Post post4 = postFacade.findPostById(4).get();

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