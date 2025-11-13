package com.taller;

import com.models.Course;
import com.models.Participation;
import com.models.Section;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.SectionRepository;
import com.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Participation> participations;
    private final List<Section> sections;
    private final StudentRepository studentRepo;
    private final ParticipationRepository participationRepo;

    private final CourseRepository courseRepo;
    private final SectionRepository sectionRepo;

    public PartiManager(int maxStudents) {
        this.MAX_STUDENTS = maxStudents;
        this.studentRepo = new StudentRepository();
        this.courseRepo = new CourseRepository();
        this.sectionRepo = new SectionRepository();
        this.participationRepo = new ParticipationRepository();

        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    private boolean isFull(List<Student> students) {
        return students.size() >= MAX_STUDENTS;
    }

    public boolean registerStudent(String dni, String fullName) {
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
            IO.println("There's no students registered.");
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
        if (this.participations.isEmpty()) {
            IO.println("There's not participations registered.");
            return;
        }
        for (Participation item : this.participations) {
            Student student = studentRepo.getStudentByUuid(
                item.getStudentUuid(),
                this.students
            );
            IO.println(
                "The student: " + student.fullName + " has participated."
            );
        }
    }

    public boolean registerCourse(String name, String code) {
        if (courseRepo.getByCode(code, this.courses) != null) {
            IO.println("Course code already exists.");
            return false;
        }
        if (courseRepo.getByName(name, this.courses) != null) {
            IO.println("Invalid name.");
            return false;
        }

        Course course = new Course(name, code);

        this.courses.add(course);
        return true;
    }

    public void showCourses() {
        if (this.courses.isEmpty()) {
            IO.println("there's no courses registered.");
            return;
        }
        for (Course item : this.courses) {
            IO.print("Course: " + item.name + " ");
            IO.println("Uuid: " + item.code);
        }
    }

    public boolean registerSection(String name, String courseCode) {
        if (sectionRepo.getByName(name, this.sections) != null) {
            IO.println("Section already exists.");
            return false;
        }
        Course course = this.courseRepo.getByCode(courseCode, this.courses);
        if (course == null) {
            IO.println("Invalid code.");
            return false;
        }
        Section section = new Section(name, course.getUuid());
        this.sections.add(section);
        return true;
    }

    public void showSections() {
        if (this.sections.isEmpty()) {
            IO.println("There's not sections registered.");
            return;
        }
        for (Section item : this.sections) {
            IO.print("Section: " + item.getName() + " ");
            IO.println(
                "Course: " +
                    this.courseRepo.getByUuid(
                        item.getCourse(),
                        this.courses
                    ).name
            );
        }
    }

    public void showRanking() {
        participationRepo.showRanking(
            this.participations,
            this.students,
            this.studentRepo
        );
    }
}
