package com.repository;

import com.models.Section;
import com.models.StudentSection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SectionRepository {

    public List<StudentSection> addStudentToSection(
        UUID studentUuid,
        UUID sectionUuid,
        List<StudentSection> relations
    ) {
        boolean alreadyExists = relations
            .stream()
            .anyMatch(
                r ->
                    r.getStudentUuid().equals(studentUuid) &&
                    r.getSection().equals(sectionUuid)
            );

        if (alreadyExists) return relations; // ya está inscrito

        relations.add(new StudentSection(studentUuid, sectionUuid));
        return relations;
    }

    public List<UUID> getStudentUuidsBySection(
        UUID sectionUuid,
        List<StudentSection> relations
    ) {
        List<UUID> filtered = new ArrayList<>();
        for (StudentSection relation : relations) {
            if (relation.getSection().equals(sectionUuid)) filtered.add(
                relation.getStudentUuid()
            );
        }
        return filtered;
    }

    public Section getByName(String name, List<Section> items) {
        for (Section item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
