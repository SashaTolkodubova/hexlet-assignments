package exercise;

import java.security.PublicKey;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void fileKVTest1() {
        HashMap<String, String> startingDataBase = new HashMap<>();
        startingDataBase.put("key1", "value1");
        startingDataBase.put("key2", "value2");
        startingDataBase.put("key3", "value3");
        String path = "src/test/resources/file";

        HashMap<String, String> dataBaseForTest = new HashMap<>();
        dataBaseForTest.put("key1", "value1");
        dataBaseForTest.put("key2", "value2");
        dataBaseForTest.put("key3", "value3");

        FileKV fileKV = new FileKV(path, startingDataBase);
        assertThat(fileKV.toMap()).isEqualTo(dataBaseForTest);

        fileKV.set("key4", "value4");
        dataBaseForTest.put("key4", "value4");
        assertThat(fileKV.toMap()).isEqualTo(dataBaseForTest);

        fileKV.unset("key2");
        dataBaseForTest.remove("key2");
        assertThat(fileKV.toMap()).isEqualTo(dataBaseForTest);
    }
    // END
}
