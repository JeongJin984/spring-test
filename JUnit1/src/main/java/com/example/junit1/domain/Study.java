package com.example.junit1.domain;

import com.example.junit1.study.StudyStatus;

import javax.persistence.*;

@Entity
public class Study {
    @Id
    private Long studyId;
    private StudyStatus status;
    private Integer maxNumber;
    private String name;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    private Member owner;

    public Study() {
        this.status = StudyStatus.DRAFT;
    }

    public Study(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Study(Long studyId, StudyStatus status, Integer maxNumber, String name, Member owner) {
        this.studyId = studyId;
        this.status = status;
        this.maxNumber = maxNumber;
        this.name = name;
        this.owner = owner;
    }

    public Study(int maxNumber, String name) {
        this.maxNumber = maxNumber;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public String getName() {
        return name;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public void setOwner(Member member) {
        this.owner = member;
    }

    public Member getOwner() {
        return this.owner;
    }
}
