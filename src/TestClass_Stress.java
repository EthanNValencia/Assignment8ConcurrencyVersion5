import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testRuntime4ParallelThreadsANDSingleThread_STRESS(){
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
        System.out.println("4-Thread runtime: " + stp.getRunTime());

        System.out.println("Sum: " + threadSaverFirstQuart.getArraySum() + " " + threadSaverSecondQuart.getArraySum() + " " + threadSaverThirdQuart.getArraySum() + " " + threadSaverFourthQuart.getArraySum());
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
        System.out.println("Single Thread runtime: " +  stp2.getRunTime() + " sum= " + total.getArraySum());
        assertEquals(total.getArraySum(), threadSaverFirstQuart.getArraySum()+threadSaverSecondQuart.getArraySum()+threadSaverThirdQuart.getArraySum()+threadSaverFourthQuart.getArraySum());
    }

    /***
     * This is a stress test that runs single, double, triple, and quad threads consecutively.
     */
    @Test
    public void test1234_STRESS(){
        int[] array = ag.generateArray(200000000);

        ThreadSaver ts1_single = new ThreadSaver(0, 1, "t", array);
        Thread tot = new Thread(new CounterThread(ts1_single));
        tot.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SystemTimeProcessor stp_single = new SystemTimeProcessor(ts1_single);
        long single_SUM = ts1_single.getArraySum();
        System.out.println("Single Thread runtime: " +  stp_single.getRunTime() + " Sum: " + single_SUM);

        ThreadSaver ts1_double = new ThreadSaver(0, 0.5,"t", array);
        ThreadSaver ts2_double = new ThreadSaver(0.5, 1,"t", array);
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
        long double_SUM = ts1_double.getArraySum()+ts2_double.getArraySum();
        System.out.println("Double-Thread runtime: " + stp_double.getRunTime() + " Sum: " + double_SUM);

        ThreadSaver ts1_triple = new ThreadSaver(0, 0.33,"t", array);
        ThreadSaver ts2_triple = new ThreadSaver(0.33, 0.66,"t", array);
        ThreadSaver ts3_triple = new ThreadSaver(0.66, 1,"t", array);
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
        long triple_SUM = ts1_triple.getArraySum()+ts2_triple.getArraySum()+ts3_triple.getArraySum();
        System.out.println("Triple-Thread runtime: " + stp_triple.getRunTime() + " Sum: " + triple_SUM);

        ThreadSaver ts1_quad = new ThreadSaver(0, 0.25,"t", array);
        ThreadSaver ts2_quad = new ThreadSaver(0.25, 0.5,"t", array);
        ThreadSaver ts3_quad = new ThreadSaver(0.5, 0.75,"t", array);
        ThreadSaver ts4_quad = new ThreadSaver(0.75, 1,"t", array);
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
        long quad_SUM = ts1_quad.getArraySum()+ts2_quad.getArraySum()+ts3_quad.getArraySum()+ts4_quad.getArraySum();
        System.out.println("  Quad-Thread runtime: " + stp_quad.getRunTime() + " Sum: " + quad_SUM);

    }
}
