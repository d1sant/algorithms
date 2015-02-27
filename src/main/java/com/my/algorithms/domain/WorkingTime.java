package com.my.algorithms.domain;

public class WorkingTime {

    private int start;
    private int end;

    public WorkingTime(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + start + ", " + end + '}';
    }
}
