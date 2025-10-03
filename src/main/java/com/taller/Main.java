package com.taller;

import java.util.Scanner;

public class Main {

    private static final int MAX_STUDENTS = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartiManager manager = new PartiManager(MAX_STUDENTS);
        String option;
        String dni;

        do {
            Ui.showMenu();
            option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "1" -> {
                    dni = Read.readDni(scanner);
                    String fullName = Read.readFullName(scanner);
                    manager.registerStudent(dni, fullName);
                }
                case "2" -> manager.showStudents();
                case "3" -> {
                    dni = Read.readDni(scanner);
                    manager.registerParticipation(dni);
                }
                case "4" -> manager.showParticipations();
                case "5" -> {
                    // manager.showParticipations();
                    System.out.print("Enter Course: ");
                    String course = Read.readInputString(scanner);
                    System.out.print("Enter Code: ");
                    String code = Read.readInputString(scanner);
                    System.out.print("Enter Description: ");
                    String description = Read.readInputString(scanner);
                    manager.registerCourse(course, description, code);
                }
                case "6" -> manager.showCourses();
                case "0" -> System.out.println("Bye!");
                default -> System.out.println("Invalid option.");
            }
        } while (!option.equals("0"));
    }
}
