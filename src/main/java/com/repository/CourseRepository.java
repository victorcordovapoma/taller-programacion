package com.repository;

import com.models.Course;
import java.util.List;
import utils.JsonHelper;

public class CourseRepository {

    private final String coursesFile = "data/courses.json";

    public List<Course> getAll() {
        return JsonHelper.readListFromFile(coursesFile, Course.class);
    }

    public void saveAll(List<Course> list) {
        JsonHelper.writeListToFile(coursesFile, list);
    }
}
