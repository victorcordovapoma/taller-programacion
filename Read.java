import java.util.Scanner;
import utils.Validation;

public class Read {

    static String readDni(Scanner sc) {
        while (true) {
            System.out.print("Enter DNI (8 digits) or ‘q’ to cancel: ");
            String dni = sc.nextLine().trim();
            if (dni.equalsIgnoreCase("q")) {
                return null; // usamos null para indicar cancelación
            }
            if (Validation.isValidDni(dni)) {
                return dni;
            }
            System.out.println("Invalid DNI. It must be exactly 8 digits.");
        }
    }

    static String readFullName(Scanner sc) {
        while (true) {
            System.out.print("Enter full name (or ‘q’ to cancel): ");
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
}
