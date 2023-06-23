package exercise;

import java.util.ArrayList;


// BEGIN
public class App {
    public static void main(String[] args) {}
    public static boolean scrabble(String chars, String word) {
    ArrayList<String> letters = getArrayFromString(chars.toLowerCase());
    ArrayList<String> wordAsArray = getArrayFromString(word.toLowerCase());
    StringBuilder newWord = new StringBuilder();
        for (String i : wordAsArray) {
            if ( letters.contains(i)) {
                newWord.append(i);
                letters.remove(i);
            }
        }
        return newWord.toString().equals(word.toLowerCase());
    }

    static ArrayList<String> getArrayFromString(String word) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            result.add(String.valueOf(word.charAt(i)));
        }
        return result;
    }
}
//END
