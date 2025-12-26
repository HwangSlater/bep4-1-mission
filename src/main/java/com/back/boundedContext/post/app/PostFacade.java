package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.RsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostWriteUseCase postWriteUseCase;
    private final PostSyncMemberUseCase postSyncMemberUseCase;
    private final PostSupport postSupport;

    @Transactional(readOnly = true)
    public long count() {
        return postSupport.count();
    }

    @Transactional
    public RsData<Post> write(PostMember author, String title, String content) {
        return postWriteUseCase.write(author, title, content);
    }

    @Transactional(readOnly = true)
    public Optional<Post> findWalletById(int id) {
        return postSupport.findWalletById(id);
    }

    @Transactional
    public PostMember syncMember(MemberDto member) {
        return postSyncMemberUseCase.syncMember(member);
    }


    @Transactional(readOnly = true)
    public Optional<PostMember> findMemberByUsername(String username) {
        return postSupport.findMemberByUsername(username);
    }
}