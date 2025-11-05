package com.models;

import java.util.UUID;

public class Course {

    private UUID uuid;
    public final String name;
    public final String code;

    public Course(String name, String code) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.code = code;
    }

    public UUID getUuid() {
        return uuid;
    }
}
