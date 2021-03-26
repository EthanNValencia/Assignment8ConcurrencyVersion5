import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * This is a class that contains various stressful tests. These tests work on my personal computer, but it is difficult to say how they function on a different computer. Each test takes around 1 minute to run. To complete all the stress tests will take time and they do put a lot of pressure on the CPU. I would think that these tests will pass on any CPU. If the tests are failing, then it is likely that a different application is competing with the IDE.
 */
public class TestClass_Stress {

    ArrayGenerator ag = new ArrayGenerator();

    /***
     * Verifies that the ArrayGenerator.generateArray sum is calculated accurately even if the nSize is significant.
     */
    @Test
    public void testGenerateArraySUM_STRESS(){
        int total = 0;
        int[] array = ag.generateArray(2000000); // lower this value to increase overall testing times.

        for(int i =0; i < array.length; i++){
            total = total + array[i];
        }

        assertEquals(ag.getSum(), total);
    }

    /***
     * This test outputs the times it takes for the system to perform
     */
    @Test
    public void testRuntime_STRESS(){
        int[] array = ag.generateArray(200000000); // This is what takes the most time.
        ThreadSaver threadSaverFirstQuart = new ThreadSaver(0, 0.25, "q1", array);
        ThreadSaver threadSaverSecondQuart = new ThreadSaver(0.25, 0.5, "q2", array);
        ThreadSaver threadSaverThirdQuart = new ThreadSaver(0.5, 0.75, "q3", array);
        ThreadSaver threadSaverFourthQuart = new ThreadSaver(0.75, 1, "q4", array);

        Thread ctFirstQuart = new Thread(new CounterThread(threadSaverFirstQuart));
        Thread ctSecondQuart = new Thread(new CounterThread(threadSaverSecondQuart));
        Thread ctThirdQuart = new Thread(new CounterThread(threadSaverThirdQuart));
        Thread ctFourthQuart = new Thread(new CounterThread(threadSaverFourthQuart));
        ctFirstQuart.start(); // start 1st quarter; (0%-25%)
        ctSecondQuart.start(); // start 2nd quarter; (25%-50%)
        ctThirdQuart.start(); // start 3rd quarter; (50%-75%)
        ctFourthQuart.start(); // start 4th quarter; (75%-100%)

        try {
            Thread.sleep(1000); // 1 second should be enough time for the sub-threads to complete.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SystemTimeProcessor stp = new SystemTimeProcessor(threadSaverFirstQuart,threadSaverSecondQuart, threadSaverThirdQuart, threadSaverFourthQuart);
        //System.out.println("4-Thread runtime: " + stp.getRunTime());
        //System.out.println("Sum: " + threadSaverFirstQuart.getArraySum() + " " + threadSaverSecondQuart.getArraySum() + " " + threadSaverThirdQuart.getArraySum() + " " + threadSaverFourthQuart.getArraySum());

        try {
            Thread.sleep(1000); // 1 second should be enough time for the sub-threads to complete.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadSaver total = new ThreadSaver(0, 1, "total", array);
        Thread tot = new Thread(new CounterThread(total));
        tot.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp2 = new SystemTimeProcessor(total);
        //System.out.println("Single Thread runtime: " +  stp2.getRunTime() + " sum= " + total.getArraySum());
        assertEquals(ag.getSum(), total.getArraySum());
        assertEquals(total.getArraySum(), threadSaverFirstQuart.getArraySum()+threadSaverSecondQuart.getArraySum()+threadSaverThirdQuart.getArraySum()+threadSaverFourthQuart.getArraySum());
    }

    /***
     * This is a stress test that runs single, double, triple, quad, five, ten, twenty threads in loops and verifies the accuracy of their counts. This test can take over 1 minute to run.
     */
    @Test
    public void test123451020_STRESS(){
        long single_SUM = 0;
        long double_SUM = 0;
        long triple_SUM = 0;
        long quad_SUM = 0;
        long fifth_SUM = 0;
        long ten_SUM = 0;
        long twenty_SUM = 0;

        int[] array = ag.generateArray(200000000);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {

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
            //System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM);
            assertEquals(ag.getSum(), single_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
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
            //System.out.println("Double-Thread runtime: " + stp_double.getRunTime() + " Sum: " + double_SUM);
            assertEquals(ag.getSum(), double_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 4; i++) {
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
            //System.out.println("Triple-Thread runtime: " + stp_triple.getRunTime() + " Sum: " + triple_SUM);
            assertEquals(ag.getSum(), triple_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
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
            //System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " +
            assertEquals(ag.getSum(), quad_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
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
            //System.out.println("  Five-Thread runtime: " + stp_fifth.getRunTime() + " Sum: " + fifth_SUM);
            assertEquals(ag.getSum(), fifth_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
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
            //System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + " Sum: " + ten_SUM);
            assertEquals(ag.getSum(), ten_SUM);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 5; i++) {
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


            //System.out.println("Twenty-Thread runtime: " + (stp1_20.getRunTime() + stp2_20.getRunTime()) + " Sum: " + twenty_SUM);
            assertEquals(ag.getSum(), twenty_SUM);
        }

        // If a == b, b == c, and c == d, then it follows that a == d.
        assertEquals(ag.getSum(), single_SUM);
        assertEquals(single_SUM, double_SUM);
        assertEquals(double_SUM, triple_SUM);
        assertEquals(triple_SUM, quad_SUM);
        assertEquals(quad_SUM, fifth_SUM);
        assertEquals(fifth_SUM, ten_SUM);
        assertEquals(ten_SUM, twenty_SUM);
    }

    /***
     * I wrote the ThreadSpinner class thinking it might have better controllability, but the performance is really bad. NOTE: If close all non-essential programs running on my PC, the speed noticeably improves.
     */
    @Test
    public void testThreadSpinners_STRESS() {
        long ten_SUM = 0;
        long quad_SUM = 0;
        long single_SUM = 0;
        int[] array = ag.generateArray(200000000);

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_ten = new SystemTimeProcessor(ts1_ten, ts2_ten, ts3_ten, ts4_ten, ts5_ten, ts6_ten, ts7_ten, ts8_ten, ts9_ten, ts10_ten);
        ten_SUM = ts1_ten.getArraySum() + ts2_ten.getArraySum() + ts3_ten.getArraySum() + ts4_ten.getArraySum() + ts5_ten.getArraySum() + ts6_ten.getArraySum() + ts7_ten.getArraySum() + ts8_ten.getArraySum() + ts9_ten.getArraySum() + ts10_ten.getArraySum();
        //System.out.println("   Ten-Thread runtime(ThreadSpinner): " + stp_ten.getRunTime() + " Sum: " + ten_SUM);
        assertEquals(ag.getSum(), ten_SUM);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_quad = new SystemTimeProcessor(ts1_quad, ts2_quad, ts3_quad, ts4_quad);
        quad_SUM = ts1_quad.getArraySum() + ts2_quad.getArraySum() + ts3_quad.getArraySum() + ts4_quad.getArraySum();
        //System.out.println("  Quad-Thread runtime(ThreadSpinner): " + stp_quad.getRunTime() + " Sum: " + quad_SUM);
        assertEquals(ag.getSum(), quad_SUM);

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_single = new SystemTimeProcessor(single);
        single_SUM = single.getArraySum();
        //System.out.println("Single-Thread runtime(ThreadSpinner): " + stp_single.getRunTime() + " Sum: " + single_SUM);
        assertEquals(ag.getSum(), single_SUM);

        assertEquals(ag.getSum(), ten_SUM);
        assertEquals(ten_SUM, quad_SUM);
        assertEquals(quad_SUM, single_SUM);
    }

    /***
     * This isn't a test. I didn't want to have to write all the code for the fifty thread test. I decided it would be better for me to write code that generates code that I can copy and paste.
     */
    //@Test
    public void generateJavaForThe50Threads(){
        double one = 0;
        double two = 0.02;
        for(int i = 1; i < 51; i ++){
            System.out.println("ThreadSaver ts" + i + " = new ThreadSaver(" + one + "," + two + ", \"t\", array);");
            one = one + 0.02;
            two = two + 0.02;
            BigDecimal bd_one = new BigDecimal(one).setScale(2, RoundingMode.HALF_UP);
            BigDecimal bd_two = new BigDecimal(two).setScale(2, RoundingMode.HALF_UP);
            one = bd_one.doubleValue();
            two = bd_two.doubleValue();
            System.out.println("Thread t" + i + " = new Thread(new CounterThread(ts" + i + "));");
            System.out.println("t" + i +".start();");
        }

        String str1 = "";
        int log = 0;
        for(int a = 1; a < 6; a ++){
            str1 = "SystemTimeProcessor stp" + a + "= new SystemTimeProcessor(";
            for(int j = 1; j < 11; j++) {
                str1 = str1.concat("ts" + (j+log) + ", ");
            }
            log = log + 10;
            System.out.println(str1 + ");");
        }
        str1 = "fifty_SUM = ";
        for(int i = 1; i < 51; i++){
            str1 = str1.concat("ts" + i + ".getArraySum()+");
        }
        System.out.println(str1 + ";");

    }

    /***
     * I am going to test how well 50 threads will run. It runs a single thread first to offer a baseline comparison.
     */
    @Test
    public void test50Threads_STRESS() {
        long single_SUM = 0;
        long fifty_SUM = 0;
        ArrayGenerator ag = new ArrayGenerator();
        int[] array = ag.generateArray(200000000);

        for(int i = 0; i < 5; i++) {

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
            //System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM);
            assertEquals(ag.getSum(), single_SUM);
        }

        for (int i = 0; i < 5; i++) {
            ThreadSaver ts1 = new ThreadSaver(0.0,0.02, "t", array);
            Thread t1 = new Thread(new CounterThread(ts1));
            t1.start();
            ThreadSaver ts2 = new ThreadSaver(0.02,0.04, "t", array);
            Thread t2 = new Thread(new CounterThread(ts2));
            t2.start();
            ThreadSaver ts3 = new ThreadSaver(0.04,0.06, "t", array);
            Thread t3 = new Thread(new CounterThread(ts3));
            t3.start();
            ThreadSaver ts4 = new ThreadSaver(0.06,0.08, "t", array);
            Thread t4 = new Thread(new CounterThread(ts4));
            t4.start();
            ThreadSaver ts5 = new ThreadSaver(0.08,0.1, "t", array);
            Thread t5 = new Thread(new CounterThread(ts5));
            t5.start();
            ThreadSaver ts6 = new ThreadSaver(0.1,0.12, "t", array);
            Thread t6 = new Thread(new CounterThread(ts6));
            t6.start();
            ThreadSaver ts7 = new ThreadSaver(0.12,0.14, "t", array);
            Thread t7 = new Thread(new CounterThread(ts7));
            t7.start();
            ThreadSaver ts8 = new ThreadSaver(0.14,0.16, "t", array);
            Thread t8 = new Thread(new CounterThread(ts8));
            t8.start();
            ThreadSaver ts9 = new ThreadSaver(0.16,0.18, "t", array);
            Thread t9 = new Thread(new CounterThread(ts9));
            t9.start();
            ThreadSaver ts10 = new ThreadSaver(0.18,0.2, "t", array);
            Thread t10 = new Thread(new CounterThread(ts10));
            t10.start();
            ThreadSaver ts11 = new ThreadSaver(0.2,0.22, "t", array);
            Thread t11 = new Thread(new CounterThread(ts11));
            t11.start();
            ThreadSaver ts12 = new ThreadSaver(0.22,0.24, "t", array);
            Thread t12 = new Thread(new CounterThread(ts12));
            t12.start();
            ThreadSaver ts13 = new ThreadSaver(0.24,0.26, "t", array);
            Thread t13 = new Thread(new CounterThread(ts13));
            t13.start();
            ThreadSaver ts14 = new ThreadSaver(0.26,0.28, "t", array);
            Thread t14 = new Thread(new CounterThread(ts14));
            t14.start();
            ThreadSaver ts15 = new ThreadSaver(0.28,0.3, "t", array);
            Thread t15 = new Thread(new CounterThread(ts15));
            t15.start();
            ThreadSaver ts16 = new ThreadSaver(0.3,0.32, "t", array);
            Thread t16 = new Thread(new CounterThread(ts16));
            t16.start();
            ThreadSaver ts17 = new ThreadSaver(0.32,0.34, "t", array);
            Thread t17 = new Thread(new CounterThread(ts17));
            t17.start();
            ThreadSaver ts18 = new ThreadSaver(0.34,0.36, "t", array);
            Thread t18 = new Thread(new CounterThread(ts18));
            t18.start();
            ThreadSaver ts19 = new ThreadSaver(0.36,0.38, "t", array);
            Thread t19 = new Thread(new CounterThread(ts19));
            t19.start();
            ThreadSaver ts20 = new ThreadSaver(0.38,0.4, "t", array);
            Thread t20 = new Thread(new CounterThread(ts20));
            t20.start();
            ThreadSaver ts21 = new ThreadSaver(0.4,0.42, "t", array);
            Thread t21 = new Thread(new CounterThread(ts21));
            t21.start();
            ThreadSaver ts22 = new ThreadSaver(0.42,0.44, "t", array);
            Thread t22 = new Thread(new CounterThread(ts22));
            t22.start();
            ThreadSaver ts23 = new ThreadSaver(0.44,0.46, "t", array);
            Thread t23 = new Thread(new CounterThread(ts23));
            t23.start();
            ThreadSaver ts24 = new ThreadSaver(0.46,0.48, "t", array);
            Thread t24 = new Thread(new CounterThread(ts24));
            t24.start();
            ThreadSaver ts25 = new ThreadSaver(0.48,0.5, "t", array);
            Thread t25 = new Thread(new CounterThread(ts25));
            t25.start();
            ThreadSaver ts26 = new ThreadSaver(0.5,0.52, "t", array);
            Thread t26 = new Thread(new CounterThread(ts26));
            t26.start();
            ThreadSaver ts27 = new ThreadSaver(0.52,0.54, "t", array);
            Thread t27 = new Thread(new CounterThread(ts27));
            t27.start();
            ThreadSaver ts28 = new ThreadSaver(0.54,0.56, "t", array);
            Thread t28 = new Thread(new CounterThread(ts28));
            t28.start();
            ThreadSaver ts29 = new ThreadSaver(0.56,0.58, "t", array);
            Thread t29 = new Thread(new CounterThread(ts29));
            t29.start();
            ThreadSaver ts30 = new ThreadSaver(0.58,0.6, "t", array);
            Thread t30 = new Thread(new CounterThread(ts30));
            t30.start();
            ThreadSaver ts31 = new ThreadSaver(0.6,0.62, "t", array);
            Thread t31 = new Thread(new CounterThread(ts31));
            t31.start();
            ThreadSaver ts32 = new ThreadSaver(0.62,0.64, "t", array);
            Thread t32 = new Thread(new CounterThread(ts32));
            t32.start();
            ThreadSaver ts33 = new ThreadSaver(0.64,0.66, "t", array);
            Thread t33 = new Thread(new CounterThread(ts33));
            t33.start();
            ThreadSaver ts34 = new ThreadSaver(0.66,0.68, "t", array);
            Thread t34 = new Thread(new CounterThread(ts34));
            t34.start();
            ThreadSaver ts35 = new ThreadSaver(0.68,0.7, "t", array);
            Thread t35 = new Thread(new CounterThread(ts35));
            t35.start();
            ThreadSaver ts36 = new ThreadSaver(0.7,0.72, "t", array);
            Thread t36 = new Thread(new CounterThread(ts36));
            t36.start();
            ThreadSaver ts37 = new ThreadSaver(0.72,0.74, "t", array);
            Thread t37 = new Thread(new CounterThread(ts37));
            t37.start();
            ThreadSaver ts38 = new ThreadSaver(0.74,0.76, "t", array);
            Thread t38 = new Thread(new CounterThread(ts38));
            t38.start();
            ThreadSaver ts39 = new ThreadSaver(0.76,0.78, "t", array);
            Thread t39 = new Thread(new CounterThread(ts39));
            t39.start();
            ThreadSaver ts40 = new ThreadSaver(0.78,0.8, "t", array);
            Thread t40 = new Thread(new CounterThread(ts40));
            t40.start();
            ThreadSaver ts41 = new ThreadSaver(0.8,0.82, "t", array);
            Thread t41 = new Thread(new CounterThread(ts41));
            t41.start();
            ThreadSaver ts42 = new ThreadSaver(0.82,0.84, "t", array);
            Thread t42 = new Thread(new CounterThread(ts42));
            t42.start();
            ThreadSaver ts43 = new ThreadSaver(0.84,0.86, "t", array);
            Thread t43 = new Thread(new CounterThread(ts43));
            t43.start();
            ThreadSaver ts44 = new ThreadSaver(0.86,0.88, "t", array);
            Thread t44 = new Thread(new CounterThread(ts44));
            t44.start();
            ThreadSaver ts45 = new ThreadSaver(0.88,0.9, "t", array);
            Thread t45 = new Thread(new CounterThread(ts45));
            t45.start();
            ThreadSaver ts46 = new ThreadSaver(0.9,0.92, "t", array);
            Thread t46 = new Thread(new CounterThread(ts46));
            t46.start();
            ThreadSaver ts47 = new ThreadSaver(0.92,0.94, "t", array);
            Thread t47 = new Thread(new CounterThread(ts47));
            t47.start();
            ThreadSaver ts48 = new ThreadSaver(0.94,0.96, "t", array);
            Thread t48 = new Thread(new CounterThread(ts48));
            t48.start();
            ThreadSaver ts49 = new ThreadSaver(0.96,0.98, "t", array);
            Thread t49 = new Thread(new CounterThread(ts49));
            t49.start();
            ThreadSaver ts50 = new ThreadSaver(0.98,1.0, "t", array);
            Thread t50 = new Thread(new CounterThread(ts50));
            t50.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SystemTimeProcessor stp1= new SystemTimeProcessor(ts1, ts2, ts3, ts4, ts5, ts6, ts7, ts8, ts9, ts10);
            SystemTimeProcessor stp2= new SystemTimeProcessor(ts11, ts12, ts13, ts14, ts15, ts16, ts17, ts18, ts19, ts20);
            SystemTimeProcessor stp3= new SystemTimeProcessor(ts21, ts22, ts23, ts24, ts25, ts26, ts27, ts28, ts29, ts30);
            SystemTimeProcessor stp4= new SystemTimeProcessor(ts31, ts32, ts33, ts34, ts35, ts36, ts37, ts38, ts39, ts40);
            SystemTimeProcessor stp5= new SystemTimeProcessor(ts41, ts42, ts43, ts44, ts45, ts46, ts47, ts48, ts49, ts50);

            fifty_SUM = ts1.getArraySum()+ts2.getArraySum()+ts3.getArraySum()+ts4.getArraySum()+ts5.getArraySum()+ts6.getArraySum()+ts7.getArraySum()+ts8.getArraySum()+ts9.getArraySum()+ts10.getArraySum()+ts11.getArraySum()+ts12.getArraySum()+ts13.getArraySum()+ts14.getArraySum()+ts15.getArraySum()+ts16.getArraySum()+ts17.getArraySum()+ts18.getArraySum()+ts19.getArraySum()+ts20.getArraySum()+ts21.getArraySum()+ts22.getArraySum()+ts23.getArraySum()+ts24.getArraySum()+ts25.getArraySum()+ts26.getArraySum()+ts27.getArraySum()+ts28.getArraySum()+ts29.getArraySum()+ts30.getArraySum()+ts31.getArraySum()+ts32.getArraySum()+ts33.getArraySum()+ts34.getArraySum()+ts35.getArraySum()+ts36.getArraySum()+ts37.getArraySum()+ts38.getArraySum()+ts39.getArraySum()+ts40.getArraySum()+ts41.getArraySum()+ts42.getArraySum()+ts43.getArraySum()+ts44.getArraySum()+ts45.getArraySum()+ts46.getArraySum()+ts47.getArraySum()+ts48.getArraySum()+ts49.getArraySum()+ts50.getArraySum();
            //System.out.println(" Fifty-Thread runtime: " + (stp1.getRunTime()+stp2.getRunTime()+stp3.getRunTime()+stp4.getRunTime()+stp5.getRunTime()) + " Sum: " + fifty_SUM);
            assertEquals(ag.getSum(), fifty_SUM);
        }
    }

    /***
     * Tests the accuracy of the ThreadSpinner under stressful conditions.
     */
    @Test
    public void threadSpinner_STRESS(){
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
            //System.out.println("Single-Thread runtime: " + stp_single.getRunTime() + " Sum: " + single_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), single_SUM);
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
            //System.out.println("Double-Thread runtime: " + stp_double.getRunTime() + " Sum: " + double_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), double_SUM);
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
            //System.out.println("Triple-Thread runtime: " + stp_triple.getRunTime() + " Sum: " + triple_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), triple_SUM);
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
            //System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " + quad_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), quad_SUM);
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
            //System.out.println("  Five-Thread runtime: " + stp_fifth.getRunTime() + " Sum: " + fifth_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), fifth_SUM);
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
            //System.out.println("   Ten-Thread runtime: " + stp_ten.getRunTime() + " Sum: " + ten_SUM + " (used ThreadSpinner)");
            assertEquals(ag.getSum(), ten_SUM);
        }
    }
}
