package com.msttech.digitalattendence.data.model;

/**
 * Created by Mahim on 4/3/2018.
 */

public class TeacherModel {

    private String imageUrl;
    private String teacherName;
    private String teacherDesignation;
    private String id;
    private String department;

    // prevent default constructor

    private TeacherModel(){}

    public TeacherModel(String imageUrl, String teacherName, String teacherDesignation,String department) {
        this.imageUrl = imageUrl;
        this.teacherName = teacherName;
        this.teacherDesignation = teacherDesignation;
        this.department = department;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDesignation() {
        return teacherDesignation;
    }

    public void setTeacherDesignation(String teacherDesignation) {
        this.teacherDesignation = teacherDesignation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
