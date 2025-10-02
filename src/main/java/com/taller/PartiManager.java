package com.taller;

import com.models.Course;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import participation.Participation;
import utils.JsonHelper;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    private final List<Participation> participations;
    private final StudentRepository studentRepo;
    private final ParticipationRepository participationRepo;
    private final CourseRepository courseRepo;

    private final String studentsFile = "data/students.json";
    private final String participationsFile = "data/participations.json";

    public PartiManager(int maxStudents) {
        this.MAX_STUDENTS = maxStudents;
        this.studentRepo = new StudentRepository();
        this.participationRepo = new ParticipationRepository();
        this.courseRepo = new CourseRepository();

        this.students = new ArrayList<>(
            JsonHelper.readListFromFile(studentsFile, Student.class)
        );
        this.participations = new ArrayList<>(
            JsonHelper.readListFromFile(participationsFile, Participation.class)
        );
    }

    private boolean isFull(List<Student> students) {
        return students.size() >= MAX_STUDENTS;
    }

    public boolean registerStudent(String dni, String fullName) {
        List<Student> students = studentRepo.getAllStudents();

        if (isFull(students)) {
            IO.println("Student list is full.");
            return false;
        }

        if (studentRepo.getStudentByDni(dni)) {
            IO.println("Invalid DNI.");
            return false;
        }
        if (studentRepo.fullNameAlreadyExist(fullName)) {
            IO.println("Invalid Name.");
            return false;
        }
        IO.println(
            "full name exits?: " + studentRepo.fullNameAlreadyExist(fullName)
        );
        Student student = new Student(dni, fullName);
        students.add(student);
        studentRepo.saveAll(students);
        return true;
    }

    public void showStudents() {
        for (Student item : students) {
            IO.println("DNI: " + item.dni + " fullName: " + item.fullName);
        }
    }

    public boolean registerParticipation(String studentDni) {
        List<Student> students = studentRepo.getAllStudents();
        List<Participation> participations = participationRepo.getAll();

        // Student found = null;

        Student student = findStudentByDni(students, studentDni);
        if (student == null) {
            return false;
        }
        Participation participation = new Participation(student);

        participations.add(participation);
        participationRepo.saveAll(participations);

        return true;
    }

    public void showParticipations() {
        List<Participation> participations = participationRepo.getAll();
        for (Participation item : participations) {
            IO.println(
                "The student: " +
                    item.getStudent().fullName +
                    " has participated."
            );
        }
    }

    public boolean registerCourse(String name, String description) {
        List<Course> courses = courseRepo.getAll();
        Course course = new Course(name, description);

        courses.add(course);
        courseRepo.saveAll(courses);

        return true;
    }

    public void showCourses() {
        List<Course> courses = courseRepo.getAll();
        for (Course item : courses) {
            IO.print("Course: " + item.name + " ");
            IO.println("Description: " + item.description);
        }
    }

    private Student findStudentByDni(List<Student> students, String dni) {
        for (Student item : students) {
            if (item.dni.equals(dni)) return item;
        }

        return null;
    }
}
