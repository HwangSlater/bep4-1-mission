package com.back.boundedContext.member.in;

import com.back.boundedContext.member.app.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/api/v1")
public class MemberController {
    private final MemberFacade memberFacade;

    @GetMapping("/members/randomSecureTip")
    public String getRandomSecureTip() {
        return memberFacade.getRandomSecureTip();
    }
}
