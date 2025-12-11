package com.services;

import com.models.Course;
import com.models.Participation;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import com.taller.PartiManager;

public class ParticipationService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final ParticipationRepository participationRepo;
    private final PartiManager manager;

    public ParticipationService(PartiManager manager) {
        this.manager = manager;
        this.studentRepo = new StudentRepository();
        this.participationRepo = new ParticipationRepository();
        this.courseRepo = new CourseRepository();
    }

    public boolean registerParticipation(String code, String studentDni) {
        Course course = courseRepo.getByCode(code, this.manager.courses);
        if (course == null) {
            System.out.println("El curso con código '" + code + "' no existe.");
            return false;
        }

        Student student = studentRepo.getStudentByDni(
            studentDni,
            this.manager.students
        );
        if (student == null) {
            System.out.println("El alumno no está registrado en el sistema.");
            return false;
        }

        boolean isEnrolled = course
            .getStudents()
            .stream()
            .anyMatch(s -> s.dni.equals(student.dni));

        if (!isEnrolled) {
            System.out.println(
                "El alumno no está inscrito en el curso " + course.name + "."
            );
            return false;
        }

        Participation participation = new Participation(student.getUuid());

        student.addParticipation(participation);
        this.manager.participations.add(participation);

        System.out.println("Participación registrada correctamente:");
        System.out.println("   Alumno: " + student.fullName);
        System.out.println(
            "   Curso: " + course.name + " (" + course.code + ")"
        );
        System.out.println(
            "   Total participaciones del alumno: " +
                student.getParticipationCount()
        );

        return true;
    }

    public void countStudentParticipations(String dni) {
        Student student = studentRepo.getStudentByDni(
            dni,
            this.manager.students
        );
        if (student == null) {
            System.out.println(
                "El alumno con DNI " + dni + " no está registrado."
            );
            return;
        }

        int count = student.getParticipationCount();
        System.out.println(
            "El alumno " +
                student.fullName +
                " tiene " +
                count +
                " participaciones registradas."
        );
    }

    public void showParticipations() {
        if (this.manager.participations.isEmpty()) {
            System.out.println("There's not participations registered.");
            return;
        }
        for (Participation item : this.manager.participations) {
            Student student = studentRepo.getStudentByUuid(
                item.getStudentUuid(),
                this.manager.students
            );
            System.out.println(
                "The student: " + student.fullName + " has participated."
            );
        }
    }

    public void showRanking(String code) {
        participationRepo.showRanking(code, this.courseRepo, this.manager);
    }

    public void calculateParticipation(String courseCode, String dniStudent) {
        if (
            courseCode == null ||
            courseCode.isEmpty() ||
            dniStudent == null ||
            dniStudent.isEmpty()
        ) {
            System.out.println("Código de curso o DNI no válidos.");
            return;
        }

        Course course = courseRepo.getByCode(courseCode, this.manager.courses);
        if (course == null) {
            System.out.println(
                "El curso con código '" + courseCode + "' no existe."
            );
            return;
        }

        Student student = studentRepo.getStudentByDni(
            dniStudent,
            this.manager.students
        );
        if (student == null) {
            System.out.println("El alumno no está registrado en el sistema.");
            return;
        }

        long studentParticipations = student.getParticipationCount();

        String level;
        if (studentParticipations >= 10) {
            level = "Alta";
        } else if (studentParticipations >= 5) {
            level = "Media";
        } else if (studentParticipations > 0) {
            level = "Baja";
        } else {
            level = "Sin participación registrada";
        }

        System.out.println("Curso: " + course.name + " (" + course.code + ")");
        System.out.println(
            "Alumno: " + student.fullName + " | DNI: " + student.dni
        );
        System.out.println(
            "Participaciones registradas: " + studentParticipations
        );
        System.out.println("Nivel de participación: " + level);
    }
}
