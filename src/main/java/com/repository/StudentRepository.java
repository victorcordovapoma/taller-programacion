package repository;

import java.util.List;
import models.Student;
import utils.JsonHelper;

public class StudentRepository {

    private final String studentsFile = "data/students.json";

    public List<Student> getAllStudents() {
        return JsonHelper.readListFromFile(studentsFile, Student.class);
    }

    public void saveAll(List<Student> students) {
        JsonHelper.writeListToFile(studentsFile, students);
    }
}
