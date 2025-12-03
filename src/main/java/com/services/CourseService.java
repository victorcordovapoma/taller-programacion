package com.services;

import com.models.Course;
import com.repository.CourseRepository;
import com.taller.PartiManager;

public class CourseService {

    private final CourseRepository courseRepo;

    private final PartiManager manager;

    public CourseService(PartiManager manager, int maxStudents) {
        this.manager = manager;
        this.courseRepo = new CourseRepository();
    }

    public boolean registerCourse(String name, String code) {
        if (this.courseRepo.getByCode(code, this.manager.courses) != null) {
            System.out.println("Course code already exists.");
            return false;
        }
        if (this.courseRepo.getByName(name, this.manager.courses) != null) {
            System.out.println("Invalid name.");
            return false;
        }

        Course course = new Course(name, code);

        this.manager.courses.add(course);
        return true;
    }

    public void showCourses() {
        if (this.manager.courses.isEmpty()) {
            System.out.println("there's no courses registered.");
            return;
        }
        for (Course item : this.manager.courses) {
            System.out.print("Course: " + item.name + " ");
            System.out.println("Uuid: " + item.code);
        }
    }

    public boolean deleteCourseByCode(String code) {
        Course course = this.courseRepo.getByCode(code, this.manager.courses);

        if (course == null) {
            System.out.println("Course not found for code: " + code);
            return false;
        }

        boolean removed = this.manager.courses.remove(course);

        if (removed) {
            System.out.println(
                "Course '" + course.name + "' deleted successfully."
            );
        } else {
            System.out.println("Error deleting the course.");
        }

        return removed;
    }
}
