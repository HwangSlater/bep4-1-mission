package com.back.global.initData;

import com.back.boundedContext.member.app.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class MemberDataInit {
    private final MemberDataInit self;
    private final MemberFacade memberFacade;

    public MemberDataInit(@Lazy MemberDataInit self, MemberFacade memberJoinUseCase) {
        this.self = self;
        this.memberFacade = memberJoinUseCase;
    }

    @Bean(name = "baseInitDataRunner1")
    @Order(1)
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
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
}