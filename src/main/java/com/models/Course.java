package com.models;

import java.util.UUID;

public class Course {

    private UUID uuid;
    public final String name;
    public final UUID code;

    public Course(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.code = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
}
