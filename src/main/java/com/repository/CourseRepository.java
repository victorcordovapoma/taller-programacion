package com.repository;

import com.models.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseRepository {

    public List<Course> getAll() {
        return new ArrayList<>();
    }

    public Course getByUuid(UUID uuid, List<Course> courses) {
        for (Course item : courses) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    public Course getByCode(String code, List<Course> courses) {
        for (Course item : courses) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
