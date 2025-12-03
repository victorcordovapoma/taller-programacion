package com.services;

import com.models.Participation;
import com.models.Student;
import com.repository.ParticipationRepository;
import com.repository.StudentRepository;
import com.taller.PartiManager;

public class ParticipationService {
    private final StudentRepository studentRepo;
    private final ParticipationRepository participationRepo;
    private final PartiManager manager;

    public ParticipationService(PartiManager manager) {
        this.manager = manager;
        this.studentRepo = new StudentRepository();
        this.participationRepo = new ParticipationRepository();
    }

    public boolean registerParticipation(String studentDni) {
        Student student = studentRepo.getStudentByDni(
            studentDni,
            this.manager.students
        );
        if (student == null) {
            return false;
        }
        Participation participation = new Participation(student.getUuid());
        this.manager.participations.add(participation);
        return true;
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

    public void showRanking() {
        participationRepo.showRanking(
            this.manager.participations,
            this.manager.students,
            this.studentRepo
        );
    }

    public String calculateParticipation(String dni, int participationsCount) {

        Student student = studentRepo.getStudentByDni(dni, this.manager.students);
        if (student == null) {
            return "Estudiante no encontrado.";
        }

        return participationRepo.calculateParticipation(
            student,
            participationsCount
        );
    }
}
