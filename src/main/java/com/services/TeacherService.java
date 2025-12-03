package com.services;

import com.models.Teacher;
import com.repository.TeacherRepository;
import com.taller.PartiManager;

public class TeacherService {

    private final TeacherRepository teacherRepo;
    private final PartiManager manager;

    public TeacherService(PartiManager manager) {
        this.manager = manager;
        this.teacherRepo = new TeacherRepository();
    }

    public boolean register(String dni, String fullName) {
        if (teacherRepo.getByDni(dni, this.manager.teachers) != null) {
            System.out.println("Invalid DNI.");
            return false;
        }

        Teacher new_item = new Teacher(dni, fullName);
        this.manager.teachers.add(new_item);
        return true;
    }

    public void show(String dni) {
        Teacher data_db = teacherRepo.getByDni(dni, this.manager.teachers);
        if (data_db == null) {
            System.out.println("Teacher doesn't exist.");
            return;
        }
        for (Teacher item : this.manager.teachers) {
            System.out.println(
                "DNI: " + item.dni + " fullName: " + item.fullName
            );
        }
    }
}
