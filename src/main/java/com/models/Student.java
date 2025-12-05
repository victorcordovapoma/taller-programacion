package com.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {

    private UUID uuid;
    public final String dni;
    public final String fullName;
    private final List<Participation> participations;

    public Student(String dni, String fullName) {
        this.uuid = UUID.randomUUID();
        this.dni = dni;
        this.fullName = fullName;
        this.participations = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addParticipation(Participation participation) {
        this.participations.add(participation);
    }

    public List<Participation> getParticipations() {
        return this.participations;
    }

    public int getParticipationCount() {
        return this.participations.size();
    }
}
