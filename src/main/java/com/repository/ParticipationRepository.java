package com.repository;

import com.models.Participation;
import com.models.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParticipationRepository {

    public List<Participation> getAll() {
        return new ArrayList<>();
    }

    public void showRanking(
        List<Participation> participations,
        List<Student> students,
        StudentRepository studentRepository
    ) {
        if (participations.isEmpty()) {
            IO.println("There's not participations registered.");
            return;
        }

        // Contar participaciones por estudiante
        Map<UUID, Long> participationCount = participations
            .stream()
            .collect(
                Collectors.groupingBy(
                    Participation::getStudentUuid,
                    Collectors.counting()
                )
            );

        // Ordenar de mayor a menor
        List<Map.Entry<UUID, Long>> ranking = participationCount
            .entrySet()
            .stream()
            .sorted(Map.Entry.<UUID, Long>comparingByValue().reversed())
            .collect(Collectors.toList());

        int position = 1;
        for (Map.Entry<UUID, Long> entry : ranking) {
            UUID studentId = entry.getKey();
            long count = entry.getValue();

            Student student = studentRepository.getStudentByUuid(
                studentId,
                students
            );
            if (student != null) {
                IO.println(
                    position +
                        ". " +
                        student.fullName +
                        " → " +
                        count +
                        " participations"
                );
                position++;
            }
        }
    }

    // Calcular participacion
    public String calculateParticipation(
        Student student,
        int participations,
        int totalSessions,
        String registeredBy
    ) {
        if (student == null || participations < 0 || totalSessions <= 0 ||
            registeredBy == null || registeredBy.isEmpty()) {
            return "Verifique los datos ingresados.";
        }

        double percentage = (participations * 100.0) / totalSessions;
        String level =
            percentage > 70 ? "Alta" :
            percentage >= 40 ? "Media" : "Baja";

        return "Alumno: " + student.fullName +
               "\nPorcentaje: " + String.format("%.2f", percentage) + "%" +
               "\nClasificación: " + level;
    }

}
