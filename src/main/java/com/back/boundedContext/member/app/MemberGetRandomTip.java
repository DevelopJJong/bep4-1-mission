package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.MemberPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberGetRandomTip {
    private final MemberPolicy memberPolicy;

    public String getRandomSecureTip(){
        return "비밀번호 변경기간은 %d일 입니다.".formatted(memberPolicy.getNeedToChangePasswordDays());
    }
}
