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
            System.out.println("El DNI ya existe.");
            return false;
        }

        Student student = new Student(dni, fullName);
        this.manager.students.add(student);
        return true;
    }

    public void showStudent(String dni) {
        if (this.manager.students.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        Student student = studentRepo.getStudentByDni(
            dni,
            this.manager.students
        );

        if (student == null) {
            System.out.println("El alumno no existe -DNI: " + dni);
            return;
        }
        System.out.println(
            "Estudiante " + student.fullName + " cuyo dni es " + student.dni
        );
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
                "Estudiante '" + student.fullName + "' eliminado exitosamente."
            );
        } else {
            System.out.println("Error deleting the student.");
        }

        return removed;
    }
}
