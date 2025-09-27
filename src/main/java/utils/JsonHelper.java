package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonHelper {

    private static final Gson gson = new Gson();

    public static <T> List<T> readListFromFile(String path, Class<T> clazz) {
        File file = new File(path);
        if (!file.exists()) {
            return List.of();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = TypeToken.getParameterized(
                List.class,
                clazz
            ).getType();
            List<T> list = gson.fromJson(reader, listType);
            return (list != null ? list : List.of());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static <T> void writeListToFile(String path, List<T> list) {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
