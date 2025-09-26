import java.util.*;

public class PartiManager {

    private final int MAX_STUDENTS;
    private final List<Student> students;
    // private final List<Session> sessions;
    private final List<Participation> participations;

    public PartiManager(int maxStudents) {
        this.MAX_STUDENTS = maxStudents;
        this.students = new ArrayList<>();
        // this.sessions = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    private boolean isFull() {
        return students.size() >= MAX_STUDENTS;
    }

    private boolean dniAlreadyExist(String dni) {
        for (Student item : students) {
            if (item.dni.equals(dni)) {
                return true;
            }
        }
        return false;
    }

    // Registro de estudiante
    public boolean registerStudent(String dni, String fullName) {
        if (isFull()) {
            IO.println("Student list is full.");
            return false;
        }
        if (dniAlreadyExist(dni)) {
            IO.println("Invalid DNI.");
            return false;
        }
        if (fullNameAlreadyExist(dni)) {
            IO.println("Invalid Name.");
            return false;
        }
        Student student = new Student(dni, fullName);
        students.add(student);
        return true;
    }

    private boolean fullNameAlreadyExist(String fullName) {
        for (Student item : students) {
            if (item.fullName.equalsIgnoreCase(fullName.trim())) {
                return true;
            }
        }
        return false;
    }

    public void showStudents() {
        for (Student item : students) {
            IO.println("DNI: " + item.dni + " fullName: " + item.fullName);
        }
        // return new ArrayList<>(students);
    }

    public boolean registerParticipation(String studentDni) {
        Student item = findStudentByDni(studentDni);
        if (item == null) {
            return false;
        }
        Participation participation = new Participation(item);

        participations.add(participation);
        return true;
    }

    public void showParticipations() {
        for (Participation item : participations) {
            IO.println(
                "The studente: " +
                    item.getStudent().fullName +
                    " has participated."
            );
        }
        // return new ArrayList<>(students);
    }

    private Student findStudentByDni(String dni) {
        for (Student item : students) {
            if (item.dni.equals(dni)) return item;
        }
        return null;
    }
}

//     // Listar estudiantes (retorna copia para que no modifiquen la lista interna)

//     // Registro de sesiones
//     public Session registerSession(String sessionId) {
//         Session s = new Session(sessionId);
//         sessions.add(s);
//         return s;
//     }

//     // Registrar participación: asigna a un estudiante en una sesión

//     // Contar participaciones de cada estudiante para una sesión o mes
//     public Map<Student, Integer> countParticipations() {
//         Map<Student, Integer> map = new HashMap<>();
//         for (Participation p : participations) {
//             Student st = p.getStudent();
//             map.put(st, map.getOrDefault(st, 0) + 1);
//         }
//         return map;
//     }

//     // Obtener ranking (top3) basado en el conteo
//     public List<Student> top3() {
//         Map<Student, Integer> map = countParticipations();
//         List<Student> sorted = new ArrayList<>(map.keySet());
//         sorted.sort((a, b) -> map.get(b).compareTo(map.get(a)));
//         // devolver hasta 3, si hay menos, devolver lo que haya
//         return sorted.subList(0, Math.min(3, sorted.size()));
//     }

//     // Reiniciar participaciones (borrar todas)
//     public void resetParticipations() {
//         participations.clear();
//     }

//     // Métodos auxiliares para buscar por id o dni
//     private Session findSessionById(String id) {
//         for (Session s : sessions) {
//             if (s.getId().equals(id)) return s;
//         }
//         return null;
//     }

// }
