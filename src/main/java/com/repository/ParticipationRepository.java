package com.repository;

import com.models.Participation;
import java.util.ArrayList;
import java.util.List;

// import utils.JsonHelper;

public class ParticipationRepository {

    // private final String participationsFile = "data/participations.json";

    public List<Participation> getAll() {
        return new ArrayList<>();
        // return JsonHelper.readListFromFile(
        // participationsFile,
        // Participation.class
        // );
    }

    // public void saveAll(List<Participation> list) {
    //     JsonHelper.writeListToFile(participationsFile, list);
    // }
}
