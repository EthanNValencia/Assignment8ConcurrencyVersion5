/*
File: ThreadSpinner.java
Author: Ethan J. Nephew
Date due: March 28, 2021
Course: CEN-3024C
Description: This is the class definition of the ThreadSpinner. It is used to run many threads at once.
*/

import java.util.ArrayList;

/***
 * This is a thread control class that can run threads that are supplied to it. The idea behind this class is to provide an extra layer of control over threads. Upon testing, the over all performance is rather poor.
 */
public class ThreadSpinner extends Thread{

    private ArrayList<Thread> threadList = new ArrayList();

    /***
     * A ThreadSpinner constructor that takes 10 thread-type objects and stores them in an Array List.
     * @param thread1 Requires the first Thread object.
     * @param thread2 Requires the second Thread object.
     * @param thread3 Requires the third Thread object.
     * @param thread4 Requires the fourth Thread object.
     * @param thread5 Requires the fifth Thread object.
     * @param thread6 Requires the sixth Thread object.
     * @param thread7 Requires the seventh Thread object.
     * @param thread8 Requires the eighth Thread object.
     * @param thread9 Requires the ninth Thread object.
     * @param thread10 Requires the tenth Thread object.
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
     * A ThreadSpinner constructor that takes 5 thread-type objects and adds them to an arraylist.
     * @param thread1 Requires the first Thread object.
     * @param thread2 Requires the second Thread object.
     * @param thread3 Requires the third Thread object.
     * @param thread4 Requires the fourth Thread object.
     * @param thread5 Requires the fifth Thread object.
     */
    public ThreadSpinner(Thread thread1, Thread thread2, Thread thread3, Thread thread4, Thread thread5) {
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
        threadList.add(thread5);
    }

    /***
     * A ThreadSpinner constructor that takes 4 thread-type objects and adds them to an arraylist.
     * @param thread1 Requires the first Thread object.
     * @param thread2 Requires the second Thread object.
     * @param thread3 Requires the third Thread object.
     * @param thread4 Requires the fourth Thread object.
     */
    public ThreadSpinner(Thread thread1, Thread thread2, Thread thread3, Thread thread4) {
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);
    }

    /***
     * A ThreadSpinner constructor that takes 3 thread-type objects and adds them to an arraylist.
     * @param thread1 Requires the first Thread object.
     * @param thread2 Requires the second Thread object.
     * @param thread3 Requires the third Thread object.
     */
    public ThreadSpinner(Thread thread1, Thread thread2, Thread thread3) {
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
    }

    /***
     * A ThreadSpinner constructor that takes 2 thread-type objects and adds them to an arraylist.
     * @param thread1 Requires the first Thread object.
     * @param thread2 Requires the second Thread object.
     */
    public ThreadSpinner(Thread thread1, Thread thread2) {
        threadList.add(thread1);
        threadList.add(thread2);
    }

    /***
     * A ThreadSpinner constructor that takes 1 thread-type objects and adds them to an arraylist.
     * @param thread1 Requires the Thread object.
     */
    public ThreadSpinner(Thread thread1){
        threadList.add(thread1);
    }

    /***
     * Overridden run() method that starts all the threads that are contained in the ThreadSpinner's arrayList.
     */
    @Override
    public void run() {
        for(int i = 0; i < threadList.size(); i ++){
            threadList.get(i).start();
        }
    }

    /***
     * Overridden toString method that simply returns the size of the threadList. This is mainly used for testing purposes.
     * @return It returns the size of the ArrayList that is used to store the threads. This is used in the TestClass.java
     */
    @Override
    public String toString() {
        return "Size:" + threadList.size();
    }
}
