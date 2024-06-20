package com.example.firstapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Resit> resits;

    // Конструктор
    public Teacher(String lastName, String firstName, String middleName, List<Resit> resits) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.resits = new ArrayList<>();
    }

    // Конструктор, использующий объект ResitRequest для инициализации полей Student
    public Teacher(ResitRequest resitRequest) {
        this.lastName = extractLastName(resitRequest.getTeacher()); // Предполагаем, что здесь есть логика для извлечения фамилии
        this.firstName = extractFirstName(resitRequest.getTeacher());  // Предполагаем, что здесь есть логика для извлечения имени
        this.middleName = extractMiddleName(resitRequest.getTeacher());  // Предполагаем, что здесь есть логика для извлечения отчества
        this.resits = new ArrayList<>(); // Предполагаем, что здесь инициализация списка пересдач
    }

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

    // Переопределение методов equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return  Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(middleName, teacher.middleName) &&
                Objects.equals(lastName, teacher.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Resit> getResits() {
        return resits;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setResitList(List<Resit> resitList) {
        this.resits = resitList;
    }

    // Метод для добавления пересдачи
    public void addUniqueResit(Resit resit) {
        if (!resits.contains(resit)) {
            resits.add(resit);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
