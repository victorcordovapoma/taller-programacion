package com.models;

// import java.util.ArrayList;
// import java.util.List;
import java.util.UUID;

public class Section {

    private UUID uuid;
    private String name;
    private UUID courseUuid;

    // private List<StudentSection> studentSections = new ArrayList<>();

    public Section(String name, UUID courseUuid) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.courseUuid = courseUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getCourse() {
        return courseUuid;
    }

    // public List<StudentSection> getStudentSections() {
    //     return studentSections;
    // }

    // public void addStudentSection(StudentSection ss) {
    //     studentSections.add(ss);
    // }
}
