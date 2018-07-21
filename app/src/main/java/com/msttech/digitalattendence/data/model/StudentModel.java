package com.msttech.digitalattendence.data.model;

public class StudentModel {
    private String studentName;
    private String studentRoll;
    private String status;

    public StudentModel(String studentName, String studentRoll, String status) {
        this.studentName = studentName;
        this.studentRoll = studentRoll;
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(String studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
