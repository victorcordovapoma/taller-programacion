package com.services;

import com.models.Student;
import com.repository.StudentRepository;
import com.taller.PartiManager;
import java.util.List;

public class StudentService {

    private final StudentRepository studentRepo;
    private final PartiManager manager;
    private final int MAX_STUDENTS;

    public StudentService(PartiManager manager, int maxStudents) {
        this.manager = manager;
        this.studentRepo = new StudentRepository();
        this.MAX_STUDENTS = maxStudents;
    }

    private boolean isFull(List<Student> students) {
        return students.size() >= MAX_STUDENTS;
    }

    public boolean registerStudent(String dni, String fullName) {
        if (isFull(this.manager.students)) {
            System.out.println("Student list is full.");
            return false;
        }

        if (studentRepo.getStudentByDni(dni, this.manager.students) != null) {
            System.out.println("Invalid DNI.");
            return false;
        }

        Student student = new Student(dni, fullName);
        this.manager.students.add(student);
        return true;
    }

    public void showStudents() {
        if (this.manager.students.size() == 0) {
            System.out.println("There's no students registered.");
            return;
        }
        for (Student item : this.manager.students) {
            System.out.println(
                "DNI: " + item.dni + " fullName: " + item.fullName
            );
        }
    }

    public boolean deleteStudentByDni(String dni) {
        Student student = studentRepo.getStudentByDni(
            dni,
            this.manager.students
        );

        if (student == null) {
            System.out.println("Student not found for DNI: " + dni);
            return false;
        }

        boolean removed = this.manager.students.remove(student);

        if (removed) {
            System.out.println(
                "Student '" + student.fullName + "' deleted successfully."
            );
        } else {
            System.out.println("Error deleting the student.");
        }

        return removed;
    }
}
