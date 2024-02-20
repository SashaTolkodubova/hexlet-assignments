package exercise;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileReader;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
            writer.write(car.serialize());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        Car car = null;
        try {
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        car =  Car.unSerialize(stringBuilder.toString()); }
        catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}
// END
