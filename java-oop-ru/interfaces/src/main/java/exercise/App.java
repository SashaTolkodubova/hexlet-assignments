package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> homeList, int numberOfHouses) {
        if (numberOfHouses > homeList.size()) {
            numberOfHouses = homeList.size();
        }
        ArrayList<String> result = new ArrayList<>();
        List<Home> homeListForSorting = new ArrayList<>(List.copyOf(homeList));
        homeListForSorting.sort(Comparator.comparingDouble(Home::getArea));
        for (int i = 0; i < numberOfHouses; i++) {
            result.add(homeListForSorting.get(i).toString());
        }
        return result;
    }
}
// END
