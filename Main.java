import java.util.List;
import java.util.Scanner;
import utils.Validation;

Integer MAX_SESSIONS = 50;

Integer MAX_STUDENTS = 30;

void main() {
    Scanner scanner = new Scanner(System.in);
    Integer studentCount = 0;
    List<Student> students = List.of();
    PartiManager manager = new PartiManager(MAX_STUDENTS);
    String option;
    String dni;
    // Participation participation = new Participation("sdsd");
    // IO.println(participation.getSessionId());

    IO.println("Hello world");
    var nums = List.<Integer>of(1, 2, 3);
    IO.println(Validation.isValidDni("dni"));

    do {
        Ui.showMenu();
        option = scanner.nextLine().trim().toUpperCase();
        // if (option.isEmpty()) option = "X"; // invalida

        switch (option) {
            // switch clásico, claro para primeros ciclos
            case "1":
                dni = Read.readDni(scanner);
                String readFullName = Read.readFullName(scanner);

                manager.registerStudent(dni, readFullName);
                break;
            case "2":
                manager.showStudents();
                break;
            // case "3":
            //     registerSession();
            //     break;
            case "3":
                dni = Read.readDni(scanner);
                manager.registerParticipation(dni);
                break;
            case "4":
                manager.showParticipations();
                break;
            // case "6":
            //     computeRankingAndReset(); // calcula 3/2/1 y resetea
            //     break;
            case "0":
                System.out.println("Bye!");
                break;
            default:
                System.out.println("Invalid option.");
        }
    } while (!option.equals("0"));
    // IO.println(MAX_SESSIONS);
    // print();
}

// String readDni(Scanner sc) {
//     while (true) {
//         IO.print("Enter DNI (8 digits) or ‘q’ to cancel: ");
//         String dni = sc.nextLine().trim();
//         if (dni.equalsIgnoreCase("q")) {
//             return null; // usamos null para indicar cancelación
//         }
//         if (dni.length() == 8 && dni.matches("\\d{8}")) {
//             return dni;
//         }
//         IO.println("Invalid DNI. It must be exactly 8 digits.");
//     }
// }

void registerStudent(List<Student> students, Scanner scanner) {
    if (students.size() >= MAX_STUDENTS) {
        System.out.println("Student list is full.");
        return;
    }

    System.out.print("DNI (8 digits): ");
    String dni = scanner.nextLine().trim();
    if (dni.length() != 8) {
        System.out.println("Invalid DNI.");
        return;
    }
    System.out.print("Full name: ");
    String name = scanner.nextLine().trim();
    Student newStudent = new Student(dni, name);

    students.add(newStudent);
    // students[studentCount] = new Student(dni, name);
    // participations[studentCount] = 0; // inicia en 0
    // studentCount++;
    // System.out.println("Student registered.");
    // return students;
}

// void print() {
//     IO.println("Hello world");
// }

// public class Main {
//     // “Base de datos” muy simple (tamaños fijos para practicar arreglos)
//     static final int MAX_STUDENTS = 30;
//     static final int MAX_SESSIONS = 50;

//     // Datos del curso (una sola sección para simplificar)
//     static Student[] students = new Student[MAX_STUDENTS];
//     static int studentCount = 0;

//     // Participaciones por alumno en el MES/EVALUACIÓN actual (contador simple)
//     static int[] participations = new int[MAX_STUDENTS];

//     // Registro de sesiones (solo un número por sesión) — opcional para mostrar bucles
//     static int[] sessionIds = new int[MAX_SESSIONS];
//     static int sessionCount = 0;

//     static Scanner sc = new Scanner(System.in);

//     public static void main(String[] args) {
//         String option;
//         do {
//             showMenu();
//             option = sc.nextLine().trim().toUpperCase();
//             if (option.isEmpty()) option = "X"; // invalida

//             switch (option) { // switch clásico, claro para primeros ciclos
//                 case "1":
//                     registerStudent();
//                     break;
//                 case "2":
//                     listStudents();
//                     break;
//                 case "3":
//                     registerSession();
//                     break;
//                 case "4":
//                     registerParticipation();
//                     break;
//                 case "5":
//                     showCounts();
//                     break;
//                 case "6":
//                     computeRankingAndReset(); // calcula 3/2/1 y resetea
//                     break;
//                 case "0":
//                     System.out.println("Bye!");
//                     break;
//                 default:
//                     System.out.println("Invalid option.");
//             }
//         } while (!option.equals("0"));
//     }

//     static void listStudents() {
//         if (studentCount == 0) {
//             System.out.println("No students yet.");
//             return;
//         }
//         System.out.println("ID | DNI       | Name");
//         for (int i = 0; i < studentCount; i++) {
//             System.out.printf("%2d | %-9s | %s%n", i, students[i].dni, students[i].fullName);
//         }
//     }

//     static void registerSession() {
//         if (sessionCount >= MAX_SESSIONS) {
//             System.out.println("Session list is full.");
//             return;
//         }
//         int newId = sessionCount + 1;
//         sessionIds[sessionCount] = newId;
//         sessionCount++;
//         System.out.println("Session #" + newId + " registered.");
//     }

//     static void registerParticipation() {
//         if (studentCount == 0) { System.out.println("Register students first."); return; }
//         listStudents();
//         System.out.print("Enter student ID to add 1 participation: ");
//         String line = sc.nextLine().trim();
//         int id;
//         try {
//             id = Integer.parseInt(line);
//         } catch (NumberFormatException e) {
//             System.out.println("Not a number.");
//             return;
//         }
//         if (id < 0 || id >= studentCount) {
//             System.out.println("Invalid student ID.");
//             return;
//         }
//         participations[id] += 1;
//         System.out.println("Participation added to " + students[id].fullName);
//     }

//     static void showCounts() {
//         if (studentCount == 0) { System.out.println("No data."); return; }
//         for (int i = 0; i < studentCount; i++) {
//             System.out.println(students[i].fullName + " => " + participations[i]);
//         }
//     }

//     static void computeRankingAndReset() {
//         if (studentCount == 0) { System.out.println("No students."); return; }

//         // Usamos una función simple de “lógica” para obtener top3 por conteo.
//         int[] topIdx = ParticipationLogic.top3Indices(participations, studentCount, students);

//         System.out.println("\nTop & Bonus:");
//         if (topIdx[0] != -1) System.out.println("1st: " + students[topIdx[0]].fullName + "  +3");
//         if (topIdx[1] != -1) System.out.println("2nd: " + students[topIdx[1]].fullName + "  +2");
//         if (topIdx[2] != -1) System.out.println("3rd: " + students[topIdx[2]].fullName + "  +1");

//         // Reiniciar contadores (siguiente evaluación)
//         for (int i = 0; i < studentCount; i++) participations[i] = 0;
//         System.out.println("\nCounters reset.");
//     }
// }
