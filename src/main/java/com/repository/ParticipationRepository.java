package repository;

import java.util.List;
import participation.Participation;
import utils.JsonHelper;

public class ParticipationRepository {

    private final String participationsFile = "data/participations.json";

    public List<Participation> getAll() {
        return JsonHelper.readListFromFile(
            participationsFile,
            Participation.class
        );
    }

    public void saveAll(List<Participation> list) {
        JsonHelper.writeListToFile(participationsFile, list);
    }
}
