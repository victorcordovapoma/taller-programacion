package com.repository;

import com.models.Course;
import com.models.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseRepository {

    public List<Course> getAll() {
        return new ArrayList<>();
    }

    public Course getByUuid(UUID uuid, List<Course> courses) {
        for (Course item : courses) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    public Course getByCode(String code, List<Course> courses) {
        for (Course item : courses) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }

    public Course getByName(String name, List<Course> courses) {
        for (Course item : courses) {
            if (item.name.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean addStudentToCourse(
        String code,
        Student student,
        List<Course> courses
    ) {
        Course course = getByCode(code, courses);
        if (course == null) {
            System.out.println("El curso con código '" + code + "' no existe.");
            return false;
        }

        boolean alreadyExists = course
            .getStudents()
            .stream()
            .anyMatch(s -> s.dni.equals(student.dni));

        if (alreadyExists) {
            System.out.println("El alumno ya está inscrito en este curso.");
            return false;
        }

        course.addStudent(student);
        System.out.println(
            "Alumno " +
                student.fullName +
                " añadido al curso " +
                course.name +
                "."
        );
        return true;
    }
}
