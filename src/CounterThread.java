/***
 * A class that implements the runnable interface. This class is used to count the contents of an int[] array.
 */
public class CounterThread implements Runnable{

    ThreadSaver ts;

    /***
     * This constructor method assigns the passed ThreadSaver to the CountThread class.
     * @param ts Requires a ThreadSaver object to be assigned.
     */
    public CounterThread(ThreadSaver ts) {
        this.ts = ts;
    }

    /***
     * This is the run method for the CounterThread class. It will count through an array with the specified bounds.
     */
    @Override
    public void run() {
        ts.setBegin(System.nanoTime()); // begin measuring time
        int[] array = ts.getIntArray();
        while(ts.getIsRunning() == true) {
            for (int i = (int)ts.getLower(); i < ts.getUpper(); i++) {
                ts.setArraySum(ts.getArraySum() + array[i]);
            }
            ts.setEnd(System.nanoTime()); // end measuring time
            ts.setRunning(false);
            //threadControl.processControl(this);
        }
    }
}
