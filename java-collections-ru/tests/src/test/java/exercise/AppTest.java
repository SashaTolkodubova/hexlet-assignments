package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> actual = App.take(list, 3);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        assertThat(actual).isEqualTo(result);

        List<Integer> list1 = new ArrayList<>();

        List<Integer> actual1 = App.take(list, 3);
        List<Integer> result1 = new ArrayList<>();
        assertThat(actual1).isEqualTo(result1);

        List<Integer> actual2 = App.take(list, 3);
        List<Integer> result2 = new ArrayList<>();
        assertThat(actual2).isEqualTo(result2);
        // END
    }
}
