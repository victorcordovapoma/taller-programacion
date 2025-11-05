package com.models;

import java.util.UUID;

public class StudentSection {

    private UUID uuid;
    private UUID studentUuid;
    private UUID sectionUuid;

    public StudentSection(UUID student, UUID section) {
        this.uuid = UUID.randomUUID();
        this.studentUuid = student;
        this.sectionUuid = section;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getStudentUuid() {
        return studentUuid;
    }

    public UUID getSection() {
        return sectionUuid;
    }
}
