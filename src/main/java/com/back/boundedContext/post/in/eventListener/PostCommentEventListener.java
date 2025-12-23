package com.back.boundedContext.post.in.eventListener;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.app.MemberService;
import com.back.shared.post.event.PostCommentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PostCommentEventListener {
    private final MemberService memberService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(PostCommentCreatedEvent event) {
        Member member = memberService.findById(event.getPostCommentDto().getAuthorId()).get();

        member.increaseActivityScore(1);
    }
}