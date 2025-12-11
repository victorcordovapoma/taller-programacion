package com.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {

    private UUID uuid;
    public final String name;
    public final String code;
    public final List<Student> students;

    public Course(String name, String code) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.code = code;
        this.students = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        boolean exists = students
            .stream()
            .anyMatch(s -> s.dni.equals(student.dni));
        if (!exists) {
            students.add(student);
        }
    }
}
