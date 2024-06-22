package com.example.firstapp.model;

public class TeacherPicture {
    private String Teacher;
    private String Picture;

    public String getName() {
        return Teacher;
    }

    public String getUrl() {
        return Picture;
    }

    @Override
    public String toString() {
        return "TeacherPicture{" +
                "name='" + Teacher + '\'' +
                ", photoUrl='" + Picture + '\'' +
                '}';
    }
}
