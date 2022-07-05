package com.example.junit1.study;

import com.example.junit1.domain.Member;
import com.example.junit1.domain.Study;
import com.example.junit1.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService1() {
        MemberService memberService = mock(MemberService.class);
        StudyRepository studyRepository = mock(StudyRepository.class);
    }

    @Test
    void createStudyService2() {
        StudyService studyService = new StudyService(memberService, studyRepository);
    }

    @Test
    void createStudyService3(
            @Mock MemberService memberService,
            @Mock StudyRepository studyRepository
        ) {
        StudyService studyService = new StudyService(memberService, studyRepository);
    }

    @Test
    void stubbingTest(
            @Mock MemberService memberService,
            @Mock StudyRepository studyRepository
    ) {
        Member member = new Member();
        member.setMemberId(1L);
        member.setEmail("asdf@asdf.asdf");

        when(memberService.findById(1L))
                .thenReturn(Optional.of(member));

        Study study = new Study(10, "foo");
        Optional<Member> findById = memberService.findById(1L);
        assertTrue(findById.isPresent());
        assertEquals("asdf@asdf.asdf", findById.get().getEmail());

        doThrow(new IllegalArgumentException())
                .when(memberService).validate(1L);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        memberService.validate(2L);
    }

    @Test
    void stubbingTest2(
            @Mock MemberService memberService,
            @Mock StudyRepository studyRepository
    ) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setMemberId(1L);
        member.setEmail("asdf@asdf.asdf");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertTrue(byId.isPresent());
        assertEquals("asdf@asdf.asdf", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));
    }

    @Test
    void MockObjectTest() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setMemberId(1L);
        member.setEmail("asdf@asdf.asdf");

        Study study = new Study(10, "Test");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        Study result = studyService.createNewStudy(1L, study);

        assertEquals(member, result.getOwner());

        verify(memberService, times(1)).notify(member); // 거꾸로 해되 횟수만 맞으면
        verify(memberService, times(1)).notify(study);  // 성공한다.
        verifyNoMoreInteractions(memberService);

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);        // 거꾸로 하면 실패한다.
        inOrder.verify(memberService).notify(member);
        verifyNoMoreInteractions(memberService);
    }

    @Test
    void BDDTest() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setMemberId(1L);
        member.setEmail("asdf@asdf.asdf");

        Study study = new Study(10, "Test");

        // BDD 스타일
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        Study result = studyService.createNewStudy(1L, study);

        // Then
        assertEquals(member, result.getOwner());
        // BDD 스타일
        then(memberService).should(times(1)).notify(member);    // 거꾸로 해도 횟수만 체크해서
        then(memberService).should(times(1)).notify(study);     // 성공한다.
        then(memberService).shouldHaveNoMoreInteractions();

    }
}
