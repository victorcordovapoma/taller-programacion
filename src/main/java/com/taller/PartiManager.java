package com.taller;

import com.models.Course;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import participation.Participation;
// import repository.StudentRepository;
// import utils.IO;
import utils.JsonHelper;

// import com.utils.IO;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    // private final List<Session> sessions;
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

        // this.students = new ArrayList<>();
        // this.participations = new ArrayList<>();
        this.students = new ArrayList<>(
            JsonHelper.readListFromFile(studentsFile, Student.class)
        );
        this.participations = new ArrayList<>(
            JsonHelper.readListFromFile(participationsFile, Participation.class)
        );
    }

    // public PartiManager(int maxStudents) {
    //     // Al iniciar, intento cargar los datos guardados
    // }

    private boolean isFull(List<Student> students) {
        return students.size() >= MAX_STUDENTS;
    }

    // Registro de estudiante
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
        // return new ArrayList<>(students);
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
        // return new ArrayList<>(students);
    }

    public boolean registerCourse(String name, String description) {
        // List<Student> students = studentRepo.getAllStudents();
        List<Course> courses = courseRepo.getAll();

        // Student found = null;

        // Student student = findStudentByDni(students, studentDni);
        // if (student == null) {
        //     return false;
        // }
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
        // return new ArrayList<>(students);
    }

    private Student findStudentByDni(List<Student> students, String dni) {
        for (Student item : students) {
            if (item.dni.equals(dni)) return item;
        }

        return null;
    }

    public List<Student> top3() {
        Map<Student, Integer> map = countParticipations();
        List<Student> students = new ArrayList<>(map.keySet());
        students.sort((a, b) -> map.get(b).compareTo(map.get(a)));

        return students.subList(0, Math.min(3, students.size()));
    }

    public Map<Student, Integer> countParticipations() {
        Map<Student, Integer> map = new HashMap<>();

        for (Participation participation : participations) {
            Student student = participation.getStudent();
            map.put(student, map.getOrDefault(student, 0) + 1);
        }

        return map;
    }
}

//     // Listar estudiantes (retorna copia para que no modifiquen la lista interna)

//     // Registro de sesiones
//     public Session registerSession(String sessionId) {
//         Session s = new Session(sessionId);
//         sessions.add(s);
//         return s;
//     }

//     // Registrar participación: asigna a un estudiante en una sesión

//     // Contar participaciones de cada estudiante para una sesión o mes

//     // Obtener ranking (top3) basado en el conteo

//     // Reiniciar participaciones (borrar todas)
//     public void resetParticipations() {
//         participations.clear();
//     }

//     // Métodos auxiliares para buscar por id o dni
//     private Session findSessionById(String id) {
//         for (Session s : sessions) {
//             if (s.getId().equals(id)) return s;
//         }
//         return null;
//     }

// }
