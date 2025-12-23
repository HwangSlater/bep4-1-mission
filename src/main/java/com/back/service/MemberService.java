package com.back.service;

import com.back.entity.Member;
import com.back.exception.DomainException;
import com.back.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        findByUsername(username);

        return memberRepository.save(new Member(username, password, nickname));
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(() ->
                new DomainException("409-1", "이미 존재하는 username 입니다.")
        );
    }
}