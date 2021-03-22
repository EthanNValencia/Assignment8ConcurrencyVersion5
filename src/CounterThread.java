/***
 * A class that implements the runnable interface. This class is used to count the contents of an int[] array.
 */
public class CounterThread implements Runnable{

    ThreadSaver ts;

    public CounterThread(ThreadSaver ts) {
        this.ts = ts;
    }

    /***
     * This is the run method for the CounterThread class. It will count through an array with the specified bounds.
     */
    @Override
    public void run() {
        int[] array = ts.getIntArray();
        while(ts.getIsRunning() == true) {
            for (int i = (int)ts.getLower(); i < ts.getUpper(); i++) {
                ts.setArraySum(ts.getArraySum() + array[i]);
            }
            ts.setRunning(false);
            //threadControl.processControl(this);
        }
    }
}
