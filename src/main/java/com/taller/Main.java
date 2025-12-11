package com.taller;

import com.services.CourseService;
import com.services.ParticipationService;
import com.services.StudentService;
import com.services.TeacherService;
import java.util.Scanner;

public class Main {

    private static final int MAX_STUDENTS = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartiManager manager = new PartiManager(MAX_STUDENTS);
        ParticipationService participationService = new ParticipationService(
            manager
        );
        CourseService courseService = new CourseService(manager);
        StudentService studentService = new StudentService(
            manager,
            MAX_STUDENTS
        );
        TeacherService teacherService = new TeacherService(manager);
        String option;
        String dni;
        String fullName;

        do {
            Ui.showMenu();
            option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "1" -> {
                    dni = Read.readDni(scanner);
                    fullName = Read.readFullName(scanner);
                    studentService.registerStudent(dni, fullName);
                }
                case "2" -> {
                    System.out.print("Ingresar codigo de curso: ");
                    String code = Read.readInputString(scanner);
                    dni = Read.readDni(scanner);
                    courseService.registerStudentToCourse(code, dni);
                }
                case "3" -> {
                    dni = Read.readDni(scanner);
                    System.out.print("Ingresar codigo de curso: ");
                    String code = Read.readInputString(scanner);
                    participationService.registerParticipation(code, dni);
                }
                // case "4" -> {
                //     dni = Read.readDni(scanner);
                //     participationService.countStudentParticipations(dni);
                // }
                case "5" -> {
                    System.out.print("Ingresar nombre de curso: ");
                    String course = Read.readInputString(scanner);
                    System.out.print("Ingresar codigo de curso: ");
                    String code = Read.readInputString(scanner);
                    courseService.registerCourse(course, code);
                }
                // case "6" -> {
                //     System.out.print("Ingresar codigo de curso: ");
                //     String code = Read.readInputString(scanner);
                //     courseService.showCourse(code);
                // }
                // case "7" -> {
                //     System.out.print("Ingresar codigo de curso: ");
                //     String code = Read.readInputString(scanner);
                //     courseService.deleteCourseByCode(code);
                // }
                // case "8" -> {
                //     dni = Read.readDni(scanner);
                //     studentService.deleteStudentByDni(dni);
                // }
                case "9" -> {
                    System.out.print("Ingresar codigo de curso: ");
                    String code = Read.readInputString(scanner);

                    participationService.showRanking(code);
                }
                // case "10" -> {
                //     dni = Read.readDni(scanner);
                //     System.out.print("Ingresar codigo de curso: ");
                //     String code = Read.readInputString(scanner);
                //     participationService.calculateParticipation(code, dni);
                // }
                // case "11" -> {
                //     dni = Read.readDni(scanner);
                //     fullName = Read.readFullName(scanner);
                //     teacherService.register(dni, fullName);
                // }
                // case "12" -> {
                //     dni = Read.readDni(scanner);
                //     teacherService.show(dni);
                // }
                case "0" -> System.out.println("Bye!");
                default -> System.out.println("Invalid option.");
            }
        } while (!option.equals("0"));
    }
}
