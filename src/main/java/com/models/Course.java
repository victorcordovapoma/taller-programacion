package com.models;

public class Course {
    public final String id;
    public final String code;
    public final String name;
    public final String description;

    public Course(String name, String description, String code) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.code = code;
    }
}
