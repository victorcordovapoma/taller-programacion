package com.repository;

import com.models.Course;
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
        String courseCode,
        List<Participation> participations,
        List<Student> students,
        Course course,
        StudentRepository studentRepo
    ) {
        if (course == null) {
            System.out.println(
                "El curso con código '" + courseCode + "' no existe."
            );
            return;
        }

        if (participations.isEmpty()) {
            System.out.println(
                "No hay participaciones registradas actualmente."
            );
            return;
        }

        Map<UUID, Long> participationCount = participations
            .stream()
            .collect(
                Collectors.groupingBy(
                    Participation::getStudentUuid,
                    Collectors.counting()
                )
            );

        if (participationCount.isEmpty()) {
            System.out.println(
                "No hay participaciones registradas actualmente."
            );
            return;
        }

        List<Map.Entry<UUID, Long>> ranking = participationCount
            .entrySet()
            .stream()
            .sorted(Map.Entry.<UUID, Long>comparingByValue().reversed())
            .collect(Collectors.toList());

        System.out.println("Curso: " + course.name + " (" + course.code + ")");
        System.out.println("--------------------------------------");
        System.out.printf(
            "%-4s %-25s %-10s %-10s%n",
            "#",
            "Alumno",
            "Participaciones",
            "Nivel"
        );
        System.out.println("--------------------------------------");

        int position = 1;
        for (Map.Entry<UUID, Long> entry : ranking) {
            UUID studentId = entry.getKey();
            long count = entry.getValue();

            Student student = studentRepo.getStudentByUuid(studentId, students);
            if (student != null) {
                String level;
                if (count >= 10) level = "Alta";
                else if (count >= 5) level = "Media";
                else level = "Baja";

                System.out.printf(
                    "%-4d %-25s %-10d %-10s%n",
                    position,
                    student.fullName,
                    count,
                    level
                );
                position++;
            }
        }

        System.out.println("--------------------------------------");
    }
}
