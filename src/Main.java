/*
File: Main.java
Author: Ethan J. Nephew
Date due: March 28, 2021
Course: CEN-3024C
Description: Main method.
*/


/***
 * This is the main thread class.
 */
public class Main {

    /***
     * Main application thread. The main thread starts other threads that start more threads. I recommend to run the TestClass.java first to verify that the program will operate correctly on the accessing PC.
     * @param args An array of command line arguments.
     */
    public static void main(String[] args){
        ArrayGenerator ag = new ArrayGenerator();
        long single_SUM = 0;
        long ten_SUM = 0;
        System.out.println("Generating array... this may take some time...");
        int[] array = ag.generateArray(200000000);
        System.out.println("The array has been completed!    Sum: " + ag.getSum());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadSaver ts1_ten = new ThreadSaver(0, 0.1, "t", array);
        ThreadSaver ts2_ten = new ThreadSaver(0.1, 0.2, "t", array);
        ThreadSaver ts3_ten = new ThreadSaver(0.2, 0.3, "t", array);
        ThreadSaver ts4_ten = new ThreadSaver(0.3, 0.4, "t", array);
        ThreadSaver ts5_ten = new ThreadSaver(0.4, 0.5, "t", array);
        ThreadSaver ts6_ten = new ThreadSaver(0.5, 0.6, "t", array);
        ThreadSaver ts7_ten = new ThreadSaver(0.6, 0.7, "t", array);
        ThreadSaver ts8_ten = new ThreadSaver(0.7, 0.8, "t", array);
        ThreadSaver ts9_ten = new ThreadSaver(0.8, 0.9, "t", array);
        ThreadSaver ts10_ten = new ThreadSaver(0.9, 1, "t", array);

        Thread t1_ten = new Thread(new CounterThread(ts1_ten));
        Thread t2_ten = new Thread(new CounterThread(ts2_ten));
        Thread t3_ten = new Thread(new CounterThread(ts3_ten));
        Thread t4_ten = new Thread(new CounterThread(ts4_ten));
        Thread t5_ten = new Thread(new CounterThread(ts5_ten));
        Thread t6_ten = new Thread(new CounterThread(ts6_ten));
        Thread t7_ten = new Thread(new CounterThread(ts7_ten));
        Thread t8_ten = new Thread(new CounterThread(ts8_ten));
        Thread t9_ten = new Thread(new CounterThread(ts9_ten));
        Thread t10_ten = new Thread(new CounterThread(ts10_ten));

        ThreadSpinner threadSpinner = new ThreadSpinner(t1_ten, t2_ten, t3_ten, t4_ten, t5_ten, t6_ten, t7_ten, t8_ten, t9_ten, t10_ten);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSpinner.start();

        try {
            threadSpinner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_ten = new SystemTimeProcessor(ts1_ten, ts2_ten, ts3_ten, ts4_ten, ts5_ten, ts6_ten, ts7_ten, ts8_ten, ts9_ten, ts10_ten);
        ten_SUM = ts1_ten.getArraySum() + ts2_ten.getArraySum() + ts3_ten.getArraySum() + ts4_ten.getArraySum() + ts5_ten.getArraySum() + ts6_ten.getArraySum() + ts7_ten.getArraySum() + ts8_ten.getArraySum() + ts9_ten.getArraySum() + ts10_ten.getArraySum();
        System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + "  Sum: " + ten_SUM);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadSaver single = new ThreadSaver(0, 1, "single", array);
        Thread single_thread = new Thread(new CounterThread(single));
        ThreadSpinner single_threadSpinner = new ThreadSpinner(single_thread);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        single_threadSpinner.start();

        try {
            single_threadSpinner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_single = new SystemTimeProcessor(single);
        single_SUM = single.getArraySum();
        System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(single_SUM == ten_SUM && ten_SUM == ag.getSum()) {
            if (stp_single.getRunTime() > stp_ten.getRunTime())
                System.out.println("The ten threads counted the array " + stp_single.getRunTime() / stp_ten.getRunTime() + " times faster!");
            if (stp_single.getRunTime() < stp_ten.getRunTime())
                System.out.println("The single thread counted the array " + stp_single.getRunTime() / stp_ten.getRunTime() + " times faster!");
        } else {
            System.out.println("Something went wrong during the process. Please consider closing all other applications.");
        }
    }
}
