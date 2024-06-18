package com.example.firstapp.model;

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

    // Конструктор
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

    // Getters
    public String getCourse() {
        return course;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDegree() {
        return degree;
    }

    public String getFormOfStudy() {
        return formOfStudy;
    }

    public String getExamType() {
        return examType;
    }

    public String getCommissionRetake() {
        return commissionRetake;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getGroup() {
        return group;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getProgram() {
        return program;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getStudent() {
        return student;
    }

    // Setters
    public void setCourse(String course) {
        this.course = course;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setFormOfStudy(String formOfStudy) {
        this.formOfStudy = formOfStudy;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public void setCommissionRetake(String commissionRetake) {
        this.commissionRetake = commissionRetake;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Resit{" +
                "course='" + course + '\'' +
                ", faculty='" + faculty + '\'' +
                ", degree='" + degree + '\'' +
                ", formOfStudy='" + formOfStudy + '\'' +
                ", examType='" + examType + '\'' +
                ", commissionRetake='" + commissionRetake + '\'' +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", group='" + group + '\'' +
                ", specialty='" + specialty + '\'' +
                ", program='" + program + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", student='" + student + '\'' +
                '}';
    }
}
