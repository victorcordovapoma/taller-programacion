package utils;

public class Validation {

    public static boolean isValidDni(String dni) {
        return dni.length() == 8 && dni.matches("\\d{8}");
    }
}
