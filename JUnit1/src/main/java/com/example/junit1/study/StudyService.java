package com.example.junit1.study;

import com.example.junit1.domain.Member;
import com.example.junit1.domain.Study;
import com.example.junit1.member.MemberNotFoundException;
import com.example.junit1.member.MemberService;

import java.util.Optional;

public class StudyService {
    private final MemberService memberService;
    private final StudyRepository studyRepository;


    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;

        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study){
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: " + memberId)));
        memberService.notify(study);
        memberService.notify(member.get());
        return studyRepository.save(study);
    }
}
