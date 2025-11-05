package com.models;

import java.util.UUID;

public class Participation {

    private UUID uuid;
    private UUID studentUuid;

    public Participation(UUID student) {
        this.uuid = UUID.randomUUID();
        this.studentUuid = student;
    }

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
