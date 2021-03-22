/***
 * A class that implements the runnable interface. This class is used to count the contents of an int[] array.
 */
public class CounterThread implements Runnable{

    ThreadSaver ts;

    public CounterThread(ThreadSaver ts) {
        this.ts = ts;
    }


    @Override
    public void run() {
        int[] array = ts.getIntArray();
        while(ts.getIsRunning() == true) {
            for (int i = (int)ts.getLower(); i < ts.getUpper(); i++) {
                ts.setArraySum(ts.getArraySum() + array[i]);
            }
            System.out.println("sum: " + ts.getArraySum());

            //threadControl.processControl(this);
        }
    }
}
