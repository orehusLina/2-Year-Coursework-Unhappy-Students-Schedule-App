package com.example.firstapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String lastName;
    private String firstName;
    private String middleName;
    private String course;
    private String faculty;
    private String degree;
    private String formOfStudy;
    private String group;
    private String specialty;
    private String program;
    private List<Resit> resits;

    // Конструктор
    public Student(String lastName, String firstName, String middleName, String course, String faculty,
                   String degree, String formOfStudy, String group, String specialty, String program) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.course = course;
        this.faculty = faculty;
        this.degree = degree;
        this.formOfStudy = formOfStudy;
        this.group = group;
        this.specialty = specialty;
        this.program = program;
        this.resits = new ArrayList<>();
    }

    // Конструктор, использующий объект ResitRequest для инициализации полей Student
    public Student(ResitRequest resitRequest) {
        this.lastName = extractLastName(resitRequest.getStudent()); // Предполагаем, что здесь есть логика для извлечения фамилии
        this.firstName = extractFirstName(resitRequest.getStudent());  // Предполагаем, что здесь есть логика для извлечения имени
        this.middleName = extractMiddleName(resitRequest.getStudent());  // Предполагаем, что здесь есть логика для извлечения отчества
        this.course = resitRequest.getCourse();
        this.faculty = resitRequest.getFaculty();
        this.degree = resitRequest.getDegree();
        this.formOfStudy = resitRequest.getFormOfStudy();
        this.group = resitRequest.getGroup();
        this.specialty = resitRequest.getSpecialty();
        this.program = resitRequest.getProgram();
        this.resits = new ArrayList<>(); // Предполагаем, что здесь инициализация списка пересдач
    }

    // Пример логики для извлечения фамилии из строки ФИО
    private String extractLastName(String fullName) {
        String[] parts = fullName.split("\\s+");
        return parts.length > 0 ? parts[0] : "";
    }

    private String extractFirstName(String fullName) {
        String[] parts = fullName.split("\\s+");
        return parts.length > 1 ? parts[1] : "";
    }

    private String extractMiddleName(String fullName) {
        String[] parts = fullName.split("\\s+");
        return parts.length > 2 ? parts[2] : "";
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(lastName).append(" ").append(firstName);
        if (middleName != null && !middleName.isEmpty()) {
            fullName.append(" ").append(middleName);
        }
        return fullName.toString();
    }

    // Getters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
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

    public String getGroup() {
        return group;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getProgram() {
        return program;
    }

    public List<Resit> getResits() {
        return resits;
    }

    // Setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setResits(List<Resit> resits) {
        this.resits = resits;
    }

    // Метод для добавления пересдачи
    public void addUniqueResit(Resit resit) {
        if (!resits.contains(resit)) {
            resits.add(resit);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(middleName, student.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }

    public boolean matchesUser(User user) {
        return this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName());
    }


    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", course='" + course + '\'' +
                ", faculty='" + faculty + '\'' +
                ", degree='" + degree + '\'' +
                ", formOfStudy='" + formOfStudy + '\'' +
                ", group='" + group + '\'' +
                ", specialty='" + specialty + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
