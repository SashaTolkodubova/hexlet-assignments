package exercise;

// BEGIN
public class MinThread extends Thread {
    private final int[] array;
    private int number;

    public MinThread(int[] array) {
        this.array = array;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        number = array[0];
        for (int j : array) {
            if(j < number) {
                number = j;
            }
        }
    }
}
// END
