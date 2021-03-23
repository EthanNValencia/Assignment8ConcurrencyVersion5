/***
 * This is the main thread class.
 */
public class Main {

    /***
     * Main application thread. The main thread starts many other threads. I recommend to run the TestClass.java first to verify that the program will operate correctly.
     * @param args
     */
    public static void main(String[] args){
        ArrayGenerator ag = new ArrayGenerator();

        long single_SUM = 0;
        long ts_single_SUM = 0;
        long double_SUM = 0;
        long triple_SUM = 0;
        long quad_SUM = 0;
        long ts_quad_SUM = 0;
        long fifth_SUM = 0;
        long ten_SUM = 0;
        long ts_ten_SUM = 0;
        long twenty_SUM = 0;

        int[] array = ag.generateArray(200000000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {

            ThreadSaver ts1_single = new ThreadSaver(0, 1, "t", array);
            Thread tot = new Thread(new CounterThread(ts1_single));
            tot.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_single = new SystemTimeProcessor(ts1_single);
            single_SUM = ts1_single.getArraySum();
            System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM);
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
            ts_single_SUM = single.getArraySum();
            System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + ts_single_SUM + " (used ThreadSpinner)");

        }

        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_double = new ThreadSaver(0, 0.5, "t", array);
            ThreadSaver ts2_double = new ThreadSaver(0.5, 1, "t", array);
            Thread t1_double = new Thread(new CounterThread(ts1_double));
            Thread t2_double = new Thread(new CounterThread(ts2_double));
            t1_double.start();
            t2_double.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_double = new SystemTimeProcessor(ts1_double, ts2_double);
            double_SUM = ts1_double.getArraySum() + ts2_double.getArraySum();
            System.out.println("Double-Thread runtime: " + stp_double.getRunTime() + " Sum: " + double_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_triple = new ThreadSaver(0, 0.33, "t", array);
            ThreadSaver ts2_triple = new ThreadSaver(0.33, 0.66, "t", array);
            ThreadSaver ts3_triple = new ThreadSaver(0.66, 1, "t", array);
            Thread t1_triple = new Thread(new CounterThread(ts1_triple));
            Thread t2_triple = new Thread(new CounterThread(ts2_triple));
            Thread t3_triple = new Thread(new CounterThread(ts3_triple));
            t1_triple.start();
            t2_triple.start();
            t3_triple.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_triple = new SystemTimeProcessor(ts1_triple, ts2_triple, ts3_triple);
            triple_SUM = ts1_triple.getArraySum() + ts2_triple.getArraySum() + ts3_triple.getArraySum();
            System.out.println("Triple-Thread runtime: " + stp_triple.getRunTime() + " Sum: " + triple_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_quad = new ThreadSaver(0, 0.25, "t", array);
            ThreadSaver ts2_quad = new ThreadSaver(0.25, 0.5, "t", array);
            ThreadSaver ts3_quad = new ThreadSaver(0.5, 0.75, "t", array);
            ThreadSaver ts4_quad = new ThreadSaver(0.75, 1, "t", array);
            Thread t1_quad = new Thread(new CounterThread(ts1_quad));
            Thread t2_quad = new Thread(new CounterThread(ts2_quad));
            Thread t3_quad = new Thread(new CounterThread(ts3_quad));
            Thread t4_quad = new Thread(new CounterThread(ts4_quad));
            t1_quad.start();
            t2_quad.start();
            t3_quad.start();
            t4_quad.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_quad = new SystemTimeProcessor(ts1_quad, ts2_quad, ts3_quad, ts4_quad);
            quad_SUM = ts1_quad.getArraySum() + ts2_quad.getArraySum() + ts3_quad.getArraySum() + ts4_quad.getArraySum();
            System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " + quad_SUM);
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
            ts_quad_SUM = ts1_quad.getArraySum() + ts2_quad.getArraySum() + ts3_quad.getArraySum() + ts4_quad.getArraySum();
            System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " + ts_quad_SUM + " (used ThreadSpinner)");
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
            t1_fifth.start();
            t2_fifth.start();
            t3_fifth.start();
            t4_fifth.start();
            t5_fifth.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_fifth = new SystemTimeProcessor(ts1_fifth, ts2_fifth, ts3_fifth, ts4_fifth, ts5_fifth);
            fifth_SUM = ts1_fifth.getArraySum() + ts2_fifth.getArraySum() + ts3_fifth.getArraySum() + ts4_fifth.getArraySum() + ts5_fifth.getArraySum();
            System.out.println("  Five-Thread runtime: " + stp_fifth.getRunTime() + " Sum: " + fifth_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++) {
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

            t1_ten.start();
            t2_ten.start();
            t3_ten.start();
            t4_ten.start();
            t5_ten.start();
            t6_ten.start();
            t7_ten.start();
            t8_ten.start();
            t9_ten.start();
            t10_ten.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp_ten = new SystemTimeProcessor(ts1_ten, ts2_ten, ts3_ten, ts4_ten, ts5_ten, ts6_ten, ts7_ten, ts8_ten, ts9_ten, ts10_ten);
            ten_SUM = ts1_ten.getArraySum() + ts2_ten.getArraySum() + ts3_ten.getArraySum() + ts4_ten.getArraySum() + ts5_ten.getArraySum() + ts6_ten.getArraySum() + ts7_ten.getArraySum() + ts8_ten.getArraySum() + ts9_ten.getArraySum() + ts10_ten.getArraySum();
            System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + " Sum: " + ten_SUM);
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
            ts_ten_SUM = ts1_ten.getArraySum() + ts2_ten.getArraySum() + ts3_ten.getArraySum() + ts4_ten.getArraySum() + ts5_ten.getArraySum() + ts6_ten.getArraySum() + ts7_ten.getArraySum() + ts8_ten.getArraySum() + ts9_ten.getArraySum() + ts10_ten.getArraySum();
            System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + " Sum: " + ts_ten_SUM + " (used ThreadSpinner)");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        for(int i = 0; i < 3; i++) {
            ThreadSaver ts1_20 = new ThreadSaver(0, 0.05, "t", array);
            ThreadSaver ts2_20 = new ThreadSaver(0.05, 0.1, "t", array);
            ThreadSaver ts3_20 = new ThreadSaver(0.10, 0.15, "t", array);
            ThreadSaver ts4_20 = new ThreadSaver(0.15, 0.20, "t", array);
            ThreadSaver ts5_20 = new ThreadSaver(0.20, 0.25, "t", array);
            ThreadSaver ts6_20 = new ThreadSaver(0.25, 0.30, "t", array);
            ThreadSaver ts7_20 = new ThreadSaver(0.30, 0.35, "t", array);
            ThreadSaver ts8_20 = new ThreadSaver(0.35, 0.40, "t", array);
            ThreadSaver ts9_20 = new ThreadSaver(0.40, 0.45, "t", array);
            ThreadSaver ts10_20 = new ThreadSaver(0.45, 0.50, "t", array);
            ThreadSaver ts11_20 = new ThreadSaver(0.50, 0.55, "t", array);
            ThreadSaver ts12_20 = new ThreadSaver(0.55, 0.60, "t", array);
            ThreadSaver ts13_20 = new ThreadSaver(0.60, 0.65, "t", array);
            ThreadSaver ts14_20 = new ThreadSaver(0.65, 0.70, "t", array);
            ThreadSaver ts15_20 = new ThreadSaver(0.70, 0.75, "t", array);
            ThreadSaver ts16_20 = new ThreadSaver(0.75, 0.80, "t", array);
            ThreadSaver ts17_20 = new ThreadSaver(0.80, 0.85, "t", array);
            ThreadSaver ts18_20 = new ThreadSaver(0.85, 0.90, "t", array);
            ThreadSaver ts19_20 = new ThreadSaver(0.90, 0.95, "t", array);
            ThreadSaver ts20_20 = new ThreadSaver(0.95, 1, "t", array);

            Thread t1_20 = new Thread(new CounterThread(ts1_20));
            Thread t2_20 = new Thread(new CounterThread(ts2_20));
            Thread t3_20 = new Thread(new CounterThread(ts3_20));
            Thread t4_20 = new Thread(new CounterThread(ts4_20));
            Thread t5_20 = new Thread(new CounterThread(ts5_20));
            Thread t6_20 = new Thread(new CounterThread(ts6_20));
            Thread t7_20 = new Thread(new CounterThread(ts7_20));
            Thread t8_20 = new Thread(new CounterThread(ts8_20));
            Thread t9_20 = new Thread(new CounterThread(ts9_20));
            Thread t10_20 = new Thread(new CounterThread(ts10_20));
            Thread t11_20 = new Thread(new CounterThread(ts11_20));
            Thread t12_20 = new Thread(new CounterThread(ts12_20));
            Thread t13_20 = new Thread(new CounterThread(ts13_20));
            Thread t14_20 = new Thread(new CounterThread(ts14_20));
            Thread t15_20 = new Thread(new CounterThread(ts15_20));
            Thread t16_20 = new Thread(new CounterThread(ts16_20));
            Thread t17_20 = new Thread(new CounterThread(ts17_20));
            Thread t18_20 = new Thread(new CounterThread(ts18_20));
            Thread t19_20 = new Thread(new CounterThread(ts19_20));
            Thread t20_20 = new Thread(new CounterThread(ts20_20));

            t1_20.start();
            t2_20.start();
            t3_20.start();
            t4_20.start();
            t5_20.start();
            t6_20.start();
            t7_20.start();
            t8_20.start();
            t9_20.start();
            t10_20.start();
            t11_20.start();
            t12_20.start();
            t13_20.start();
            t14_20.start();
            t15_20.start();
            t16_20.start();
            t17_20.start();
            t18_20.start();
            t19_20.start();
            t20_20.start();


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp1_20 = new SystemTimeProcessor(ts1_20, ts2_20, ts3_20, ts4_20, ts5_20, ts6_20, ts7_20, ts8_20, ts9_20, ts10_20);
            SystemTimeProcessor stp2_20 = new SystemTimeProcessor(ts11_20, ts12_20, ts13_20, ts14_20, ts15_20, ts16_20, ts17_20, ts18_20, ts19_20, ts20_20);

            twenty_SUM = ts1_20.getArraySum() + ts2_20.getArraySum() + ts3_20.getArraySum() + ts4_20.getArraySum() + ts5_20.getArraySum()
                    + ts6_20.getArraySum() + ts7_20.getArraySum() + ts8_20.getArraySum() + ts9_20.getArraySum() + ts10_20.getArraySum()
                    + ts11_20.getArraySum() + ts12_20.getArraySum() + ts13_20.getArraySum() + ts14_20.getArraySum() + ts15_20.getArraySum()
                    + ts16_20.getArraySum() + ts17_20.getArraySum() + ts18_20.getArraySum() + ts19_20.getArraySum() + ts20_20.getArraySum();

            System.out.println("Twenty-Thread runtime: " + (stp1_20.getRunTime() + stp2_20.getRunTime()) + " Sum: " + twenty_SUM);

        }
    }
}
