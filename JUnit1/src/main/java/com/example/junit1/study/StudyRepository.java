package com.example.junit1.study;

import com.example.junit1.domain.Member;
import com.example.junit1.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
