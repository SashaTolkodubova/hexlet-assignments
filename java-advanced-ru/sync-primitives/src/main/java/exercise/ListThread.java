package exercise;

// BEGIN
public class ListThread extends Thread{
    private final SafetyList safetyList;

    public ListThread(SafetyList safetyList){
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            safetyList.add(i);
        }
    }
}
// END
