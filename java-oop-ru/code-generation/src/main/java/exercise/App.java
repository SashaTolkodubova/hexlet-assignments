package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
        writer.write(car.serialize());
        writer.close();
    }

    @SneakyThrows
    public static Car extract(Path path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        return Car.unSerialize(stringBuilder.toString());
    }
}
// END
