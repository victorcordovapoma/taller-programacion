package com.repository;

import com.models.Teacher;
import java.util.List;
import java.util.UUID;

public class TeacherRepository {

    public Teacher getByDni(String dni, List<Teacher> items) {
        for (Teacher item : items) {
            if (item.dni.equals(dni)) return item;
        }

        return null;
    }

    public Teacher getByUuid(UUID uuid, List<Teacher> items) {
        for (Teacher item : items) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    public boolean fullNameAlreadyExist(String fullName, List<Teacher> items) {
        for (Teacher item : items) {
            if (item.fullName.trim().toLowerCase().equals(fullName)) {
                return true;
            }
        }
        return false;
    }
}
