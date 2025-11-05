package com.models;

import java.util.UUID;

public class Section {

    private UUID uuid;
    private String name;
    private UUID courseUuid;

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
}
