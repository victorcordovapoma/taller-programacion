package com.taller;

import java.util.ArrayList;
import java.util.List;

import com.models.Course;
import com.models.Participation;
import com.models.Section;
import com.models.Student;
import com.repository.CourseRepository;
import com.repository.SectionRepository;

public class PartiManager {

    public List<Student> students;
    public List<Course> courses;
    public List<Participation> participations;
    private final List<Section> sections;

    private final CourseRepository courseRepo;
    private final SectionRepository sectionRepo;

    public PartiManager(int maxStudents) {
        this.courseRepo = new CourseRepository();
        this.sectionRepo = new SectionRepository();

        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.sections = new ArrayList<>();
        this.participations = new ArrayList<>();
    }

    public boolean registerSection(String name, String courseCode) {
        if (sectionRepo.getByName(name, this.sections) != null) {
            System.out.println("Section already exists.");
            return false;
        }
        Course course = this.courseRepo.getByCode(courseCode, this.courses);
        if (course == null) {
            System.out.println("Invalid code.");
            return false;
        }
        Section section = new Section(name, course.getUuid());
        this.sections.add(section);
        return true;
    }

    public void showSections() {
        if (this.sections.isEmpty()) {
            System.out.println("There's not sections registered.");
            return;
        }
        for (Section item : this.sections) {
            System.out.print("Section: " + item.getName() + " ");
            System.out.println(
                "Course: " +
                    this.courseRepo.getByUuid(
                        item.getCourse(),
                        this.courses
                    ).name
            );
        }
    }
}
