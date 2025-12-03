package com.taller;

import java.util.Scanner;

import com.services.CourseService;
import com.services.ParticipationService;
import com.services.StudentService;

public class Main {

    private static final int MAX_STUDENTS = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartiManager manager = new PartiManager(MAX_STUDENTS);
        ParticipationService participationService = new ParticipationService(manager);
        CourseService courseService = new CourseService(manager, MAX_STUDENTS);
        StudentService studentService = new StudentService(manager, MAX_STUDENTS);
        String option;
        String dni;

        do {
            Ui.showMenu();
            option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "1" -> {
                    dni = Read.readDni(scanner);
                    String fullName = Read.readFullName(scanner);
                    studentService.registerStudent(dni, fullName);
                }
                case "2" -> studentService.showStudents();
                case "3" -> {
                    dni = Read.readDni(scanner);
                    participationService.registerParticipation(dni);
                }
                case "4" -> participationService.showParticipations();
                case "5" -> {
                    System.out.print("Enter Course: ");
                    String course = Read.readInputString(scanner);
                    System.out.print("Enter Code: ");
                    String code = Read.readInputString(scanner);
                    courseService.registerCourse(course, code);
                }
                case "6" -> courseService.showCourses();
                case "7" -> {
                    System.out.print("Enter Section: ");
                    String section = Read.readInputString(scanner);
                    System.out.print("Enter Course Code: ");
                    String code = Read.readInputString(scanner);
                    manager.registerSection(section, code);
                }
                case "8" -> manager.showSections();
                case "9" -> participationService.showRanking();
                // case "9" -> manager.showRanking();
                case "10" -> {
                    dni = Read.readDni(scanner);
                    participationService.calculateParticipation(dni,
                        manager.participations.size());
                }
                case "0" -> System.out.println("Bye!");
                default -> System.out.println("Invalid option.");
            }
        } while (!option.equals("0"));
    }
}
