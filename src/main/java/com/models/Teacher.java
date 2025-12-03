package com.models;

import java.util.UUID;

public class Teacher {

    private UUID uuid;
    public final String dni;
    public final String fullName;

    public Teacher(String dni, String fullName) {
        this.uuid = UUID.randomUUID();
        this.dni = dni;
        this.fullName = fullName;
    }

    public UUID getUuid() {
        return uuid;
    }
}
