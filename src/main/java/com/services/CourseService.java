package com.services;

import com.models.Course;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.StudentRepository;
import com.taller.PartiManager;

public class CourseService {

    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    private final PartiManager manager;

    public CourseService(PartiManager manager) {
        this.manager = manager;
        this.courseRepo = new CourseRepository();
        this.studentRepo = new StudentRepository();
    }

    public boolean registerCourse(String name, String code) {
        if (this.courseRepo.getByCode(code, this.manager.courses) != null) {
            System.out.println("Ya existe un curso con el código " + code);
            return false;
        }
        if (this.courseRepo.getByName(name, this.manager.courses) != null) {
            System.out.println("El nombre del curso ya existe");
            return false;
        }

        Course course = new Course(name, code);

        this.manager.courses.add(course);
        return true;
    }

    public void showCourse(String code) {
        if (this.manager.courses.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        Course course = courseRepo.getByCode(code, this.manager.courses);
        if (course == null) {
            System.out.println("El curso no existe - Código: " + code);
            return;
        }
        System.out.println(
            "Curso " + course.name + " cuyo código es " + course.code
        );
    }

    public boolean deleteCourseByCode(String code) {
        Course course = this.courseRepo.getByCode(code, this.manager.courses);

        if (course == null) {
            System.out.println("Course not found for code: " + code);
            return false;
        }

        boolean removed = this.manager.courses.remove(course);

        if (removed) {
            System.out.println(
                "Curso '" + course.name + "' eliminado exitosamente."
            );
        } else {
            System.out.println("Error deleting the course.");
        }

        return removed;
    }

    public void registerStudentToCourse(String courseCode, String studentDni) {
        Student student = studentRepo.getStudentByDni(
            studentDni,
            this.manager.students
        );
        if (student == null) {
            IO.println("El alumno no está registrado en el sistema.");
            return;
        }

        courseRepo.addStudentToCourse(
            courseCode,
            student,
            this.manager.courses
        );
    }
}
