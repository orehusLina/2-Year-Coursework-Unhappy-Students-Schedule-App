package com.example.firstapp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Resit {
    private static int nextId = 1; // Статическая переменная для генерации ID
    private int id;
    private String subject;
    private List<Student> studentList;
    private HashSet<Teacher> teacherList;
    private String course;
    private String faculty;
    private String degree;
    private String formOfStudy;
    private String examType;
    private String commissionRetake;
    private List<String> groups;
    private List<String> specialties;
    private String date;
    private String time;
    private String place;

    // Конструктор
    public Resit(String subject, List<Student> studentList, HashSet<Teacher> teacherList, String course, String faculty, String degree,
                 String formOfStudy, String examType, String commissionRetake, List<String> groups, List<String> specialties, String date, String time, String place) {
        this.subject = subject;
        this.studentList = studentList;
        this.teacherList = teacherList;
        this.course = course;
        this.faculty = faculty;
        this.degree = degree;
        this.formOfStudy = formOfStudy;
        this.examType = examType;
        this.commissionRetake = commissionRetake;
        this.groups = groups;
        this.specialties = specialties;
        this.date = date;
        this.time = time;
        this.place = place;
        this.id = generateUniqueId();
    }

    // Getters
    //public int getId() {
    //    return id;
    //}

    public String getSubject() {
        return subject;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public HashSet<Teacher> getTeacherList() {
        return teacherList;
    }

    public String getTeacherListToString() {
        StringBuilder builder = new StringBuilder();

        for (Teacher teacher : teacherList) {
            String fullName = teacher.getLastName() + " " + teacher.getFirstName() + " " + teacher.getMiddleName();
            builder.append(fullName.trim()); // Убираем лишние пробелы
            builder.append("\n"); // Добавляем перенос строки между именами преподавателей
        }

        // Удаляем последний лишний перенос строки, если список не пустой
        if (!teacherList.isEmpty()) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }


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

    public List<String> getGroups() {
        return groups;
    }

    public List<String> getSpecialties() {
        return specialties;
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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setTeacherList(HashSet<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

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

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public void setSpecialties(List<String> specialties) {
        this.specialties = specialties;
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

    @Override
    public String toString() {
        return "Resit{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", studentList=" + studentList +
                ", teacherList=" + teacherList +
                ", course='" + course + '\'' +
                ", faculty='" + faculty + '\'' +
                ", degree='" + degree + '\'' +
                ", formOfStudy='" + formOfStudy + '\'' +
                ", examType='" + examType + '\'' +
                ", commissionRetake='" + commissionRetake + '\'' +
                ", groups=" + groups +
                ", specialties=" + specialties +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    // Приватный метод для генерации уникального ID
    private static int generateUniqueId() {
        return nextId++; // Инкрементируем статическую переменную для каждой новой пересдачи
    }

    // Метод для добавления уникального студента
    public void addUniqueStudent(Student student) {
        if (!studentList.contains(student)) {
            studentList.add(student);
        }
    }

    // Метод для добавления уникального преподавателя
    public void addUniqueTeacher(Teacher teacher) {
        if (!teacherList.contains(teacher)) {
            teacherList.add(teacher);
        }
    }

    public void addUniqueGroup(String group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
    }

    public void addUniqueSpecialty(String specialty) {
        if (!specialties.contains(specialty)) {
            specialties.add(specialty);
        }
    }

}
