package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    String filePath;


    public FileKV(String filePath, Map<String, String> startedDataBase) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(startedDataBase));

    }

    @Override
    public void set(String key, String value) {
        Map<String, String> dataBase = Utils.unserialize(Utils.readFile(filePath));
        dataBase.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(dataBase));
    }

    @Override
    public void unset(String key) {
        Map<String, String> dataBase = Utils.unserialize(Utils.readFile(filePath));
        dataBase.remove(key);
        Utils.writeFile(filePath, Utils.serialize(dataBase));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> dataBase = Utils.unserialize(Utils.readFile(filePath));
        return dataBase.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> dataBase = Utils.unserialize(Utils.readFile(filePath));
        return new HashMap<>(dataBase);
    }
}
// END
