package com.example.junit1;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JUnitAssertionTest {

    @Test
    void assertion_test(){
        Study study = new Study();
        assertNotNull(study);
        // 3번쨰 인자가 Executable 이나 문자열이냐는 조건에 맞을때 문자열 연산(concat)을 하냐 무조건 하냐의 차이
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "Initial State should be " + StudyStatus.DRAFT);
        assertFalse(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0 이상이어야 합니다\n 현재인원: " + study.getLimit() + "\n");

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "Initial State should be " + StudyStatus.DRAFT),
                () -> assertFalse(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0 이상이어야 합니다\n 현재인원: " + study.getLimit() + "\n")
        );

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(90);       // 한 95쯤 넘어가면 에러생김
        }); // Executable이 끝날때 까지 기다린다.

        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(90);       // 한 95쯤 넘어가면 에러생김
        }); // 임계점인 100ms가 넘어가면 무조건 종료한다. 하지만 Executable이 ThreadLocal으로 별도의 스레드에서 실행이 된다.
    }

}
