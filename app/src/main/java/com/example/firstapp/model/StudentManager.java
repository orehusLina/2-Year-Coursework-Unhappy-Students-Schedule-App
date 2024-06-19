package com.example.firstapp.model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;

    // Конструктор по умолчанию
    public StudentManager() {
        this.students = new ArrayList<>();
    }

    // Конструктор с параметром списка студентов
    public StudentManager(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    // Добавление студента
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Получение списка студентов
    public List<Student> getStudents() {
        return students;
    }

    // Получение студента по ID (простой пример, может быть адаптирован)
    public Student getStudentById(int id) {
        // Предполагается, что каждый студент имеет уникальный ID
        // Это простой пример и может потребоваться адаптация под вашу реализацию
        return students.get(id);
    }

    // Отчисление студента
    public void expelStudent(Student student) {
        students.remove(student);
    }

    // Отчисление студента по ID
    public void expelStudentById(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            expelStudent(student);
        }
    }

    // Полное очищение списка студентов
    public void clearAllStudents() {
        students.clear();
    }

    @Override
    public String toString() {
        return "StudentManager{" +
                "students=" + students +
                '}';
    }
}
