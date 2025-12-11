package com.taller;

import java.util.Scanner;
import java.util.UUID;
import utils.Validation;

public class Read {

    static String readDni(Scanner sc) {
        while (true) {
            System.out.print("Ingresar DNI (8 digitos) o ‘q’ para cancelar: ");
            String dni = sc.nextLine().trim();
            if (dni.equalsIgnoreCase("q")) {
                return null;
            }
            if (Validation.isValidDni(dni)) {
                return dni;
            }
            System.out.println("Invalid DNI. It must be exactly 8 digitos.");
        }
    }

    static String readFullName(Scanner sc) {
        while (true) {
            System.out.print("Enter full name (o ‘q’ para cancelar): ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("q")) {
                return null;
            }
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Name cannot be empty.");
        }
    }

    static String readInputString(Scanner sc) {
        while (true) {
            String data = sc.nextLine().trim();
            if (data.equalsIgnoreCase("q")) {
                return null;
            }
            if (!data.isEmpty()) {
                return data;
            }
            System.out.println("Data cannot be empty.");
        }
    }

    static UUID readUuid(Scanner sc) {
        while (true) {
            System.out.print("Enter UUID (or 'q' to cancel): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                return null;
            }

            try {
                return UUID.fromString(input); // ✅ convierte si es válido
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid UUID format. Please try again.");
            }
        }
    }

    static Integer readInputInt(Scanner sc) {
        while (true) {
            Integer data = sc.nextInt();
            return data;
        }
    }
}
