package com.repository;

import com.models.Student;
import java.util.List;
import java.util.UUID;

public class StudentRepository {

    public Student getStudentByDni(String dni, List<Student> students) {
        for (Student item : students) {
            if (item.dni.equals(dni)) return item;
        }

        return null;
    }

    public Student getStudentByUuid(UUID uuid, List<Student> students) {
        for (Student item : students) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    public boolean fullNameAlreadyExist(
        String fullName,
        List<Student> students
    ) {
        for (Student item : students) {
            if (item.fullName.trim().toLowerCase().equals(fullName)) {
                return true;
            }
        }
        return false;
    }
}
