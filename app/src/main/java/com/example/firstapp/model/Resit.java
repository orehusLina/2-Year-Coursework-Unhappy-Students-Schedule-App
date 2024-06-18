package com.example.firstapp.model;

import android.text.Spannable;

public class Resit {
    private String course;
    private String faculty;
    private String degree;
    private String formOfStudy;
    private String examType;
    private String commissionRetake;
    private String subject;
    private String teacher;
    private String group;
    private String specialty;
    private String program;
    private String date;
    private String time;
    private String place;
    private String student;

    // Конструктор, геттеры и сеттеры
    public Resit(String course, String faculty, String degree, String formOfStudy, String examType,
                 String commissionRetake, String subject, String teacher, String group, String specialty,
                 String program, String date, String time, String place, String student) {
        this.course = course;
        this.faculty = faculty;
        this.degree = degree;
        this.formOfStudy = formOfStudy;
        this.examType = examType;
        this.commissionRetake = commissionRetake;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.specialty = specialty;
        this.program = program;
        this.date = date;
        this.time = time;
        this.place = place;
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public String getFaculty() {
        return faculty;
    }

    // Getters and setters
    // ...
}

