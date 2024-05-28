package exercise;

// BEGIN
public class MaxThread extends Thread{

    private final int[] array;
    private int number;

    public int getNumber() {
        return number;
    }

    public MaxThread(int[] array) {
        this.array = array;
    }
    @Override
    public void run() {
        number = array[0];
        for (int j : array) {
            if (j > number) {
                number = j;
            }
        }
    }
}
// END
