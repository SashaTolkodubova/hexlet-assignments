package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        Map<String, Integer> result = new HashMap<>();

        LOGGER.info("minThread is starting");
        MinThread minThread = new MinThread(array);
        LOGGER.info("mixThread is starting");
        MaxThread maxThread = new MaxThread(array);

        minThread.start();
        maxThread.start();
        LOGGER.info("maxThread and minThread finished");

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
        result.put("min", minThread.getNumber());
        result.put("max", maxThread.getNumber());
        LOGGER.info(result.toString());
        return result;
    }
    // END
}
