package com.example.junit1;

public class Study {
    private StudyStatus status;
    private int limit;
    private String name;

    public Study() {
        this.status = StudyStatus.DRAFT;
    }

    public Study(int limit) {
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }
}
