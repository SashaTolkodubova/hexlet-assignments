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

//       length = 0
        List<Integer> list1 = new ArrayList<>();
        List<Integer> actual1 = App.take(list1, 3);

        List<Integer> result1 = new ArrayList<>();

        assertThat(actual1).isEqualTo(result1);

//        length != 0 && count > length
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        List<Integer> actual2 = App.take(list2, 4);
        List<Integer> result2 = new ArrayList<>();
        result2.add(1);
        result2.add(2);
        result2.add(3);
        assertThat(actual2).isEqualTo(result2);

//        length != 0 && count <= length
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        List<Integer> actual3 = App.take(list2, 2);
        List<Integer> result3 = new ArrayList<>();
        result3.add(1);
        result3.add(2);

        assertThat(actual3).isEqualTo(result3);
        // END
    }
}
