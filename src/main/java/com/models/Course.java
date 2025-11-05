package com.models;

import java.util.UUID;

public class Course {

    private UUID uuid;
    public final String name;
    public final String description;

    public Course(String name, String description) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }
}
