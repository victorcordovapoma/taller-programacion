package com.taller;

import com.models.Course;
import com.models.Participation;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;

// import utils.JsonHelper;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Participation> participations;
    // private final List<Course> courses;
    private final StudentRepository studentRepo;
    private final ParticipationRepository participationRepo;
    private final CourseRepository courseRepo;

    // private final String studentsFile = "data/students.json";
    // private final String participationsFile = "data/participations.json";

    public PartiManager(int maxStudents) {
        this.MAX_STUDENTS = maxStudents;
        this.studentRepo = new StudentRepository();
        this.participationRepo = new ParticipationRepository();
        this.courseRepo = new CourseRepository();

        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        // JsonHelper.readListFromFile(studentsFile, Student.class)
        this.participations = new ArrayList<>();
        // JsonHelper.readListFromFile(participationsFile, Participation.class)
        // this.students = new ArrayList<>(
        //     JsonHelper.readListFromFile(studentsFile, Student.class)
        // );
        // this.participations = new ArrayList<>(
        //     JsonHelper.readListFromFile(participationsFile, Participation.class)
        // );
        // this.courses = new ArrayList<>(
        //     JsonHelper.readListFromFile(coursesFile, Course.class)
        // );
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
        // if (studentRepo.fullNameAlreadyExist(fullName, this.students)) {
        //     IO.println("Invalid Name.");
        //     return false;
        // }
        // IO.println(
        //     "full name exits?: " +
        //         studentRepo.fullNameAlreadyExist(fullName, this.students)
        // );
        Student student = new Student(dni, fullName);
        students.add(student);
        // studentRepo.saveAll(students);
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
        // List<Student> students = studentRepo.getAllStudents();
        // List<Participation> participations = participationRepo.getAll();

        // Student found = null;

        Student student = studentRepo.getStudentByDni(
            studentDni,
            this.students
        );
        if (student == null) {
            return false;
        }
        Participation participation = new Participation(student.getUuid());

        this.participations.add(participation);
        // participationRepo.saveAll(participations);

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
        // List<Course> courses = courseRepo.getAll();
        Course course = new Course(name, description, code);

        this.courses.add(course);
        // courseRepo.saveAll(courses);

        return true;
    }

    public void showCourses() {
        // List<Course> courses = courseRepo.getAll();
        for (Course item : this.courses) {
            IO.print("Course: " + item.name + " ");
            IO.print("Description: " + item.description + " ");
            IO.println("Code: " + item.code);
        }
    }

    // private Student findStudentByDni(List<Student> students, String dni) {
    //     for (Student item : students) {
    //         if (item.dni.equals(dni)) return item;
    //     }

    //     return null;
    // }
}
