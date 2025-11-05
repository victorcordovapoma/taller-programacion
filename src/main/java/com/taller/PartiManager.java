package com.taller;

import com.models.Course;
import com.models.Participation;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Participation> participations;
    private final StudentRepository studentRepo;
    private final ParticipationRepository participationRepo;
    private final CourseRepository courseRepo;

    public PartiManager(int maxStudents) {
        this.MAX_STUDENTS = maxStudents;
        this.studentRepo = new StudentRepository();
        this.participationRepo = new ParticipationRepository();
        this.courseRepo = new CourseRepository();

        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    private boolean isFull(List<Student> students) {
        return students.size() >= MAX_STUDENTS;
    }

    public boolean registerStudent(String dni, String fullName) {
        // List<Student> students = studentRepo.getAllStudents();

        if (isFull(students)) {
            IO.println("Student list is full.");
            return false;
        }

        if (studentRepo.getStudentByDni(dni, this.students) != null) {
            IO.println("Invalid DNI.");
            return false;
        }

        Student student = new Student(dni, fullName);
        students.add(student);
        return true;
    }

    public void showStudents() {
        if (students.size() == 0) {
            IO.println("No hay cursos registrados actualmente.");
            return;
        }
        for (Student item : students) {
            IO.println("DNI: " + item.dni + " fullName: " + item.fullName);
        }
    }

    public boolean registerParticipation(String studentDni) {
        Student student = studentRepo.getStudentByDni(
            studentDni,
            this.students
        );
        if (student == null) {
            return false;
        }
        Participation participation = new Participation(student.getUuid());

        this.participations.add(participation);
        return true;
    }

    public void showParticipations() {
        List<Participation> participations = participationRepo.getAll();

        for (Participation item : participations) {
            IO.println(
                "The student: " +
                    studentRepo.getStudentByUuid(
                        item.getStudentUuid(),
                        this.students
                    ).fullName +
                    " has participated."
            );
        }
    }

    public boolean registerCourse(
        String name,
        String description,
        String code
    ) {
        Course course = new Course(name, description, code);

        this.courses.add(course);
        return true;
    }

    public void showCourses() {
        for (Course item : this.courses) {
            IO.print("Course: " + item.name + " ");
            IO.print("Description: " + item.description + " ");
            IO.println("Code: " + item.code);
        }
    }
}
