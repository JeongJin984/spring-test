package com.example.junit1.member;

import com.example.junit1.domain.Member;
import com.example.junit1.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId);

    void validate(long l);

    void notify(Study newStudy);

    void notify(Member member);
}
