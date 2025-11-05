package com.repository;

import com.models.Student;
// import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// import utils.JsonHelper;

public class StudentRepository {

    // private final String studentsFile = "data/students.json";

    // public List<Student> getAllStudents() {
    //     // List<Student> list = JsonHelper.readListFromFile(
    //     //     studentsFile,
    //     //     Student.class
    //     // );

    //     // return new ArrayList<>(list != null ? list : List.of());
    //     return new ArrayList<>();
    // }

    // public boolean getStudentByDni(String dni, List<Student> students) {
    //     // List<Student> students = getAllStudents();

    //     for (Student item : students) {
    //         if (item.dni.equals(dni)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public Student getStudentByDni(String dni, List<Student> students) {
        for (Student item : students) {
            if (item.dni.equals(dni)) return item;
        }

        return null;
    }

    public Student getStudentByUuid(UUID uuid, List<Student> students) {
        // List<Student> students = getAllStudents();

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
        // List<Student> students = getAllStudents();
        // IO.println(fullName);
        for (Student item : students) {
            // IO.println(item.fullName + "    ");
            // IO.println(fullName);
            if (item.fullName.trim().toLowerCase().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    // public void saveAll(List<Student> students) {
    //     JsonHelper.writeListToFile(studentsFile, students);
    // }
}
