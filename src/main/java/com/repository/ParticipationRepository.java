package com.repository;

import com.models.Course;
import com.models.Participation;
import com.models.Student;
import com.taller.PartiManager;
import java.util.ArrayList;
import java.util.List;

public class ParticipationRepository {

    public List<Participation> getAll() {
        return new ArrayList<>();
    }

    public void showRanking(
        String courseCode,
        CourseRepository courseRepo,
        PartiManager manager
    ) {
        // 1️⃣ Validar código de curso
        if (courseCode == null || courseCode.isEmpty()) {
            System.out.println("El código del curso no puede estar vacío.");
            return;
        }

        // 2️⃣ Buscar curso
        Course course = courseRepo.getByCode(courseCode, manager.courses);
        if (course == null) {
            System.out.println(
                "El curso con código '" + courseCode + "' no existe."
            );
            return;
        }

        // 3️⃣ Obtener los estudiantes del curso
        List<Student> students = course.getStudents();
        if (students.isEmpty()) {
            System.out.println(
                "No hay estudiantes registrados en el curso " +
                    course.name +
                    "."
            );
            return;
        }

        // 4️⃣ Calcular participaciones por alumno (ya están en Student)
        List<Student> rankedStudents = students
            .stream()
            .filter(s -> s.getParticipationCount() > 0) // solo con participaciones
            .sorted((a, b) ->
                Integer.compare(
                    b.getParticipationCount(),
                    a.getParticipationCount()
                )
            )
            .toList();

        if (rankedStudents.isEmpty()) {
            System.out.println(
                "No hay participaciones registradas actualmente en este curso."
            );
            return;
        }

        System.out.println("Curso: " + course.name + " (" + course.code + ")");
        System.out.printf(
            "%-4s %-25s %-15s %-10s%n",
            "Pos",
            "Alumno",
            "Participaciones",
            "Nivel"
        );
        System.out.println(
            "------------------------------------------------------------"
        );

        int pos = 1;
        for (Student s : rankedStudents) {
            int count = s.getParticipationCount();
            String level;
            if (count >= 10) level = "Alta";
            else if (count >= 5) level = "Media";
            else level = "Baja";

            System.out.printf(
                "%-4d %-25s %-15d %-10s%n",
                pos++,
                s.fullName,
                count,
                level
            );
        }
    }
}
