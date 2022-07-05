package com.example.junit1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Member {
    @Id
    private Long memberId;
    private String email;

    @OneToMany
    private List<Study> createdStudy;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
