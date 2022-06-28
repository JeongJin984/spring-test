package com.example.junit1;

public class Study {
    private StudyStatus status;
    private int limit;

    public Study() {
        this.status = StudyStatus.DRAFT;
    }

    public Study(int limit) {
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }
}
