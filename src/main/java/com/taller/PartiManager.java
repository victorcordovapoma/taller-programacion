package com.taller;

import com.models.Course;
import com.models.Participation;
import com.models.Student;
import com.models.Teacher;
import java.util.ArrayList;
import java.util.List;

public class PartiManager {

    public List<Student> students;
    public List<Teacher> teachers;
    public List<Course> courses;
    public List<Participation> participations;

    public PartiManager(int maxStudents) {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.participations = new ArrayList<>();
    }
}
