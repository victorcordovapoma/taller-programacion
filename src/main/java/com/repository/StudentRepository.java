package com.repository;

import java.util.List;
import com.models.Student;
import utils.JsonHelper;

public class StudentRepository {

    private final String studentsFile = "data/students.json";

    public List<Student> getAllStudents() {
        return JsonHelper.readListFromFile(studentsFile, Student.class);
    }

    public boolean getStudentByDni(String dni) {
        List<Student> students = getAllStudents();

        for (Student item : students) {
            if (item.dni.equals(dni)) {
                return true;
            }
        }
        return false;
    }

    public boolean fullNameAlreadyExist(String fullName) {
        List<Student> students = getAllStudents();
        // IO.printl(item.fullName + "    ");
        IO.println(fullName);
        for (Student item : students) {
            IO.println(item.fullName + "    ");
            IO.println(fullName);
            if (item.fullName.trim().toLowerCase().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    public void saveAll(List<Student> students) {
        JsonHelper.writeListToFile(studentsFile, students);
    }
}
