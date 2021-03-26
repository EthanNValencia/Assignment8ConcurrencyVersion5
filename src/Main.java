/***
 * This is the main thread class.
 */
public class Main {

    /***
     * Main application thread. The main thread starts other threads that start more threads. I recommend to run the TestClass.java first to verify that the program will operate correctly on the accessing PC.
     * @param args
     */
    public static void main(String[] args){
        ArrayGenerator ag = new ArrayGenerator();
        long single_SUM = 0;
        long double_SUM = 0;
        long triple_SUM = 0;
        long quad_SUM = 0;
        long fifth_SUM = 0;
        long ten_SUM = 0;

        int[] array = ag.generateArray(200000000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++){
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
            System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM + " (used ThreadSpinner)");

        }

        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_double = new ThreadSaver(0, 0.5, "t", array);
            ThreadSaver ts2_double = new ThreadSaver(0.5, 1, "t", array);
            Thread t1_double = new Thread(new CounterThread(ts1_double));
            Thread t2_double = new Thread(new CounterThread(ts2_double));
            ThreadSpinner double_threadSpinner = new ThreadSpinner(t1_double, t2_double);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double_threadSpinner.start();

            try {
                double_threadSpinner.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_double = new SystemTimeProcessor(ts1_double, ts2_double);
            double_SUM = ts1_double.getArraySum() + ts2_double.getArraySum();
            System.out.println("Double-Thread runtime: " + stp_double.getRunTime() + " Sum: " + double_SUM + " (used ThreadSpinner)");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 1; i++) {
            ThreadSaver ts1_triple = new ThreadSaver(0, 0.33, "t", array);
            ThreadSaver ts2_triple = new ThreadSaver(0.33, 0.66, "t", array);
            ThreadSaver ts3_triple = new ThreadSaver(0.66, 1, "t", array);
            Thread t1_triple = new Thread(new CounterThread(ts1_triple));
            Thread t2_triple = new Thread(new CounterThread(ts2_triple));
            Thread t3_triple = new Thread(new CounterThread(ts3_triple));
            ThreadSpinner threadSpinner = new ThreadSpinner(t1_triple, t2_triple, t3_triple);

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

            SystemTimeProcessor stp_triple = new SystemTimeProcessor(ts1_triple, ts2_triple, ts3_triple);
            triple_SUM = ts1_triple.getArraySum() + ts2_triple.getArraySum() + ts3_triple.getArraySum();
            System.out.println("Triple-Thread runtime: " + stp_triple.getRunTime() + " Sum: " + triple_SUM + " (used ThreadSpinner)");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++){

            ThreadSaver ts1_quad = new ThreadSaver(0, 0.25, "t", array);
            ThreadSaver ts2_quad = new ThreadSaver(0.25, 0.5, "t", array);
            ThreadSaver ts3_quad = new ThreadSaver(0.5, 0.75, "t", array);
            ThreadSaver ts4_quad = new ThreadSaver(0.75, 1, "t", array);
            Thread t1_quad = new Thread(new CounterThread(ts1_quad));
            Thread t2_quad = new Thread(new CounterThread(ts2_quad));
            Thread t3_quad = new Thread(new CounterThread(ts3_quad));
            Thread t4_quad = new Thread(new CounterThread(ts4_quad));

            ThreadSpinner quadSpinner = new ThreadSpinner(t1_quad, t2_quad, t3_quad, t4_quad);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            quadSpinner.start();

            try {
                quadSpinner.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_quad = new SystemTimeProcessor(ts1_quad, ts2_quad, ts3_quad, ts4_quad);
            quad_SUM = ts1_quad.getArraySum() + ts2_quad.getArraySum() + ts3_quad.getArraySum() + ts4_quad.getArraySum();
            System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " + quad_SUM + " (used ThreadSpinner)");
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_fifth = new ThreadSaver(0, 0.2, "t", array);
            ThreadSaver ts2_fifth = new ThreadSaver(0.2, 0.4, "t", array);
            ThreadSaver ts3_fifth = new ThreadSaver(0.4, 0.6, "t", array);
            ThreadSaver ts4_fifth = new ThreadSaver(0.6, 0.8, "t", array);
            ThreadSaver ts5_fifth = new ThreadSaver(0.8, 1, "t", array);
            Thread t1_fifth = new Thread(new CounterThread(ts1_fifth));
            Thread t2_fifth = new Thread(new CounterThread(ts2_fifth));
            Thread t3_fifth = new Thread(new CounterThread(ts3_fifth));
            Thread t4_fifth = new Thread(new CounterThread(ts4_fifth));
            Thread t5_fifth = new Thread(new CounterThread(ts5_fifth));
            ThreadSpinner threadSpinner = new ThreadSpinner(t1_fifth,t2_fifth,t3_fifth,t4_fifth,t5_fifth);

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

            SystemTimeProcessor stp_fifth = new SystemTimeProcessor(ts1_fifth, ts2_fifth, ts3_fifth, ts4_fifth, ts5_fifth);
            fifth_SUM = ts1_fifth.getArraySum() + ts2_fifth.getArraySum() + ts3_fifth.getArraySum() + ts4_fifth.getArraySum() + ts5_fifth.getArraySum();
            System.out.println("  Five-Thread runtime: " + stp_fifth.getRunTime() + " Sum: " + fifth_SUM + " (used ThreadSpinner)");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++){
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
            System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + " Sum: " + ten_SUM + " (used ThreadSpinner)");
        }
    }
}
