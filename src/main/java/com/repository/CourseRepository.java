package com.repository;

import java.util.List;
import com.models.Course;
// import participation.Participation;
import utils.JsonHelper;

public class CourseRepository {

    private final String participationsFile = "data/courses.json";

    public List<Course> getAll() {
        return JsonHelper.readListFromFile(participationsFile, Course.class);
    }

    public void saveAll(List<Course> list) {
        JsonHelper.writeListToFile(participationsFile, list);
    }
}
