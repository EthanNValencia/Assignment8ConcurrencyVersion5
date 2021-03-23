// This will probably be one of the last things I do.


import java.util.ArrayList;

/***
 * This is a thread control class that can run threads that are supplied to it. The idea behind this class is to provide an extra layer of control over threads. Upon testing, the over all performance is rather poor.
 */
public class ThreadSpinner extends Thread{
    ArrayList<Thread> threadList = new ArrayList();



    /***
     * A constructor that takes 10 thread-type objects.
     */
    public ThreadSpinner(Thread thread1, Thread thread2, Thread thread3, Thread thread4, Thread thread5, Thread thread6, Thread thread7, Thread thread8, Thread thread9, Thread thread10) {
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
        threadList.add(thread5);
        threadList.add(thread6);
        threadList.add(thread7);
        threadList.add(thread8);
        threadList.add(thread9);
        threadList.add(thread10);
    }

    /***
     * A constructor that takes 4 thread-type objects.
     */
    public ThreadSpinner(Thread thread1, Thread thread2, Thread thread3, Thread thread4) {
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
    }

    /***
     * A constructor that takes 1 thread-type object.
     */
    public ThreadSpinner(Thread thread1){
        threadList.add(thread1);
    }

    @Override
    public void run() {
        for(int i = 0; i < threadList.size(); i ++){
            threadList.get(i).start();
        }
    }
}
