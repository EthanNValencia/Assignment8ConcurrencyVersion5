import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    ThreadSaver ts = new ThreadSaver();
    ArrayGenerator ag = new ArrayGenerator();
    SystemTimeProcessor stp = new SystemTimeProcessor();

    /***
     * This is a test that checks that the system that is running this program has at least one core. This might be a silly test.
     */
    @Test
    public void checkSystemCores(){
        int cores = Runtime.getRuntime().availableProcessors();
        if (cores >= 1){
            assertTrue(true);
        } else if (cores < 1) {
            fail("This program will not run correctly on your PC. This program requires a processor to run correctly. @Test checkSystemCores()");
        }
    }

    /***
     * Verifies that the ThreadSaver.getIsRunning method returns true (default setting).
     */
    @Test
    public void testGetIsRunningTRUE(){
        assertTrue(ts.getIsRunning());
    }

    /***
     * Verifies that the ThreadSaver.setRunning method returns true (default setting).
     */
    @Test
    public void testSetRunningFALSE(){
        ts.setRunning(false);
        assertFalse(ts.getIsRunning());
    }

    /***
     * Verifies that the ThreadSaver.setUpper method is working. This method calculates the upper range based on a decimal value. Ex: Array length = 1000, then 0.25 = 250.
     */
    @Test
    public void testUpperSet(){
        ts.setIntArray(ag.generateArray(1000));

        ts.setUpper(0.25);

        assertEquals(250, ts.getUpper());
    }

    /***
     * Verifies that the ThreadSaver.getUpper method is working.
     */
    @Test
    public void testUpperGetZERO(){
        assertEquals(0.0, ts.getUpper());
    }

    /***
     * Verifies that the ThreadSaver.setLower method is working.
     */
    @Test
    public void testLowerSet(){
        ts.setIntArray(ag.generateArray(1000));
        ts.setLower(0.5);
        assertEquals(500, ts.getLower());
    }

    /***
     * Verifies that the ThreadSaver getLower method is working.
     */
    @Test
    public void testLowerGetZERO(){
        assertEquals(0, ts.getLower());
    }

    /***
     * Verifies that ThreadSaver.getName method is working.
     */
    @Test
    public void testNameSet(){
        ts.setName("TestName");
        assertEquals("TestName", ts.getName());
    }

    /***
     * Tests that the SystemTimeProcessor.setAverageBegin method works correctly.
     */
    @Test
    public void testSetAverageBegin(){
       stp.setAverageBegin(1000);
       assertEquals(1000, stp.getAverageBegin());
    }

    /***
     * Tests that the SystemTimeProcessor.getAverageBegin method works correctly.
     */
    @Test
    public void testGetAverageBegin(){
        assertEquals(0, stp.getAverageBegin());
    }

    /***
     * Tests that the SystemTimeProcessor.setAverageEnd method works correctly.
     */
    @Test
    public void testSetAverageEnd(){
        stp.setAverageEnd(1000);
        assertEquals(1000, stp.getAverageEnd());
    }

    /***
     * Tests that the SystemTimeProcess.getAverageEnd method works correctly.
     */
    @Test
    public void testGetAverageEnd(){
        assertEquals(0, stp.getAverageEnd());
    }

    /***
     * Verifies that the ThreadSaver.getName method is working.
     */
    @Test
    public void testNameGetNULL(){
        assertEquals(null, ts.getName());
    }

    /***
     * Verifies that ThreadSaver.setIntArray method is working.
     */
    @Test
    public void testSetIntArray(){
        int[] array = {0,1,2,3,4};
        ts.setIntArray(array);
        for(int i = 0; i < array.length; i++){
            assertEquals(i, array[i]); // 0==0, 1==1, 2==2, 3==3, 4==4
        }
    }

    /***
     * Verifies that ThreadSaver.getIntArray method is working.
     */
    @Test
    public void testGetIntArrayNULL(){
        assertEquals(null, ts.getIntArray());
    }

    /***
     * Verifies that the ThreadSaver.getIntArray method is working.
     */
    @Test
    public void testGetIntArrayNOTNULL(){
        int[] array = {0,1,2,3,4};
        ts.setIntArray(array);
        assertNotEquals(null, ts.getIntArray());
    }

    /***
     * Verifies that ThreadSaver.setBegin method is working.
     */
    @Test
    public void testSetBegin(){
        ts.setBegin(10);
        assertEquals(10, ts.getBegin());
    }

    /***
     * Verifies that the ThreadSaver.getBegin method is working.
     */
    @Test
    public void testGetBeginZERO(){
        assertEquals(0, ts.getBegin());
    }

    /***
     * Verifies that the ThreadSaver.setEnd method is working.
     */
    @Test
    public void testSetEnd(){
        ts.setEnd(50);
        assertEquals(50, ts.getEnd());
    }

    /***
     * Verifies that ThreadSaver.getEnd method is working.
     */
    @Test
    public void testGetEndZERO(){
        assertEquals(0, ts.getEnd());
    }

    /***
     * Verifies that ThreadSaver.setArraySum method is working.
     */
    @Test
    public void testSetArraySum(){
        ts.setArraySum(10);
        assertEquals(10, ts.getArraySum());
    }

    /***
     * Verifies that ThreadSaver.getArraySum method is working.
     */
    @Test
    public void testGetArraySumZERO(){
        assertEquals(0, ts.getArraySum());
    }

    /***
     * Verifies that the ArrayGenerator.setSum method is working.
     */
    @Test
    public void testSetSum(){
        ag.setSum(5000000);
        assertEquals(5000000, ag.getSum());
    }

    /***
     * Verifies that the ArrayGenerator.getSum method is working.
     */
    @Test
    public void testGetSumZero(){
        assertEquals(0, ag.getSum());
    }

    /***
     * Verifies that the nSize parameter matches the returned array.length.
     */
    @Test
    public void testGenerateArraySIZE(){
        int[] array = ag.generateArray(5000);
        assertEquals(5000, array.length);
    }

    /***
     * Independently verifies that the sum of the array, as calculated by the ArrayGenerator.generateArray is accurate.
     */
    @Test
    public void testGenerateArraySUM(){
        int total = 0;
        int[] array = ag.generateArray(5000);

        for(int i =0; i < array.length; i++){
            total = total + array[i];
        }
        assertEquals(ag.getSum(), total);
    }

    /***
     * This tests that the ThreadSaver constructor functions as intended.
     */
    @Test
    public void testThreadSaverConstructor(){
        ThreadSaver threadSaver = new ThreadSaver(0, 0.25, "Quarter1", ag.generateArray(100));
        assertEquals(0, threadSaver.getLower());
        assertEquals(25, threadSaver.getUpper());
        assertEquals("Quarter1", threadSaver.getName());
        assertEquals(100,threadSaver.getIntArray().length);
    }

    /***
     * Tests that the CounterThread.CounterThread constructor is functioning correctly.
     */
    @Test
    public void testCounterThread(){
        int[] array = ag.generateArray(10);
        ThreadSaver ts = new ThreadSaver(0, 1, "test", array);
        CounterThread ct = new CounterThread(ts);
        assertEquals(0, ct.ts.getLower());
        assertEquals(10, ct.ts.getUpper()); // 100% of 10 is 10
        assertEquals("test", ct.ts.getName());
        assertEquals(array, ct.ts.getIntArray());
    }

    /***
     * This verifies that a thread can count an entire int[] array accurately.
     */
    @Test
    public void testThreadSaverToCounterThread(){
        // This thread will count from 0-100% of the provided array.
        ThreadSaver threadSaver = new ThreadSaver(0, 1, "FullCount", ag.generateArray(100));
        Thread ct = new Thread(new CounterThread(threadSaver));
        ct.start();
        try {
            ct.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(ag.getSum(), threadSaver.getArraySum());
    }

    /***
     * This is going to test that a double threading test is functional (not running in parallel).
     */
    @Test
    public void testThreadSaverToCounterThread_DOUBLETHREAD(){
        int[] array = ag.generateArray(100);
        ThreadSaver threadSaverFirstHalf = new ThreadSaver(0, 0.5, "FullCount", array);
        ThreadSaver threadSaverSecondHalf = new ThreadSaver(0.5, 1, "FullCount", array);

        Thread ctFirstHalf = new Thread(new CounterThread(threadSaverFirstHalf));
        Thread ctSecondHalf = new Thread(new CounterThread(threadSaverSecondHalf));

        ctFirstHalf.start();
        try {
            ctFirstHalf.join();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }

        ctSecondHalf.start();
        try {
            ctSecondHalf.join();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }

        // the sum of the first half + the sum of the second half = should equal the total sum.
        // System.out.println(ag.getSum() + " = " + threadSaverFirstHalf.getArraySum() + " + " + threadSaverSecondHalf.getArraySum());
        assertEquals(ag.getSum(), threadSaverFirstHalf.getArraySum() + threadSaverSecondHalf.getArraySum());
    }

    /***
     * This tests two CounterThreads running in parallel and verifies that they are counting accurately.
     */
    @Test
    public void testTwoParallel(){
        int[] array = ag.generateArray(100);
        ThreadSaver threadSaverFirstHalf = new ThreadSaver(0, 0.5, "FullCount", array);
        ThreadSaver threadSaverSecondHalf = new ThreadSaver(0.5, 1, "FullCount", array);

        Thread ctFirstHalf = new Thread(new CounterThread(threadSaverFirstHalf));
        Thread ctSecondHalf = new Thread(new CounterThread(threadSaverSecondHalf));
        ctFirstHalf.start();
        ctSecondHalf.start();

        try {
            Thread.sleep(1000); // 1 second should be enough time for the sub-threads to complete.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(ag.getSum(), threadSaverFirstHalf.getArraySum() + threadSaverSecondHalf.getArraySum());
    }

    /***
     * This tests four CounterThreads running in parallel and counting correctly.
     */
    @Test
    public void testFourParallel(){
        int[] array = ag.generateArray(100);
        ThreadSaver threadSaverFirstQuart = new ThreadSaver(0, 0.25, "FullCount", array);
        ThreadSaver threadSaverSecondQuart = new ThreadSaver(0.25, 0.5, "FullCount", array);
        ThreadSaver threadSaverThirdQuart = new ThreadSaver(0.5, 0.75, "FullCount", array);
        ThreadSaver threadSaverFourthQuart = new ThreadSaver(0.75, 1, "FullCount", array);

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
        assertEquals(ag.getSum(), threadSaverFirstQuart.getArraySum() + threadSaverSecondQuart.getArraySum() + threadSaverThirdQuart.getArraySum() + threadSaverFourthQuart.getArraySum());
    }

    /***
     * This verifies that the SystemTimeProcessing.calcAVG is functioning.
     */
    @Test
    public void testCalcAVG(){
        long[] array = {10,10,10,10,10}; // avg is 10
        stp.calcAVG(array, 1);
        assertEquals(10, stp.getAverageEnd());
    }

    /***
     * Tests that the SystemTimeProcessor.testAverageRunTime method functions correctly.
     */
    @Test
    public void testSetAverageRunTime(){
        stp.setAverageRunTime(1000);
        assertEquals(1000, stp.getAverageRunTime());
    }

    /***
     * Tests that the SystemTimeProcessor.getAverageRunTime method is functioning correctly.
     */
    @Test
    public void testGetAverageRunTime(){
        assertEquals(0, stp.getAverageRunTime());
    }

    /***
     * Tests that the SystemTimeProcessor.getBeginTimeVariation method works correctly.
     */
    @Test
    public void testGetBeginTimeVariation(){
        assertEquals(0, stp.getBeginTimeVariation());
    }

    /***
     * Tests that the SystemTimeProcessor.setBeginTimeVariation method functions correctly.
     */
    @Test
    public void testSetBeginTimeVariation(){
        stp.setBeginTimeVariation(10);
        assertEquals(10, stp.getBeginTimeVariation());
    }

    /***
     * Tests that the SystemTimeProcessor.getEndTimeVariation method functions correctly.
     */
    @Test
    public void testGetEndTimeVariation(){
        assertEquals(0, stp.getEndTimeVariation());
    }

    /***
     * Tests that the SystemTimeProcessor.setEndTimeVariation method functions correctly.
     */
    @Test
    public void testSetEndTimeVariation(){
        stp.setEndTimeVariation(1000);
        assertEquals(1000, stp.getEndTimeVariation());
    }

    /***
     * Tests that the SystemTimeProcessor.getRunTime method functions correctly.
     */
    @Test
    public void testGetRunTime(){
        assertEquals(0, stp.getRunTime());
    }

    /***
     * Tests that the SystemTimeProcessor.setRunTime method functions correctly.
     */
    @Test
    public void testSetRunTime(){
        stp.setRunTime(5000);
        assertEquals(5000, stp.getRunTime());
    }


    /***
     * This tests that the SystemTimeProcess.calcTimeVariation method is functioning correctly.
     */
    @Test
    public void testCalcTimeVariation(){
        long[] begin = {10,10,10,10,12}; // 2 in variation
        long[] end = {10,10,10,10,20}; // 10 in variation
        stp.calcTimeVariationANDRuntime(begin, end);
        assertEquals(2, stp.getBeginTimeVariation());
        assertEquals(10, stp.getEndTimeVariation());
    }

    /***
     * This test verifies that the averate runtime is calculated correctly.
     */
    @Test
    public void testCalcAVGRuntime(){
        long[] begin = {10,10,10,10,10};
        long[] end = {12,12,12,12,12}; // avg runtime should be 2
        stp.calcAVGRuntime(begin, end);
        assertEquals(2, stp.getAverageRunTime());
    }

    /***
     * This test will be used to develop a SystemTimeProcessor class that will take times
     */
    @Test
    public void timeProcessingTest(){
        ThreadSaver ts1 = new ThreadSaver();
        ts1.setBegin(1000);
        ts1.setEnd(2000);

        ThreadSaver ts2 = new ThreadSaver();
        ts2.setBegin(1000);
        ts2.setEnd(2000);

        ThreadSaver ts3 = new ThreadSaver();
        ts3.setBegin(1000);
        ts3.setEnd(2000);

        ThreadSaver ts4 = new ThreadSaver();
        ts4.setBegin(1000);
        ts4.setEnd(2000);
        SystemTimeProcessor stp = new SystemTimeProcessor(ts1, ts2, ts3, ts4);

        assertEquals(1000, stp.getAverageBegin());
        assertEquals(2000, stp.getAverageEnd());
        assertEquals(1000, stp.getRunTime());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes one thread object is functioning.
     */
    @Test
    public void testThreadSpinner1(){
        ThreadSaver ts = new ThreadSaver();
        Thread thread = new Thread(new CounterThread(ts));
        ThreadSpinner threadSpin = new ThreadSpinner(thread);
        assertEquals("Size:1", threadSpin.toString());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes two thread objects is functioning.
     */
    @Test
    public void testThreadSpinner2(){
        ThreadSpinner threadSpin = new ThreadSpinner(new Thread(), new Thread());
        assertEquals("Size:2", threadSpin.toString());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes three thread objects is functioning.
     */
    @Test
    public void testThreadSpinner3(){
        ThreadSpinner threadSpin = new ThreadSpinner(new Thread(), new Thread(), new Thread());
        assertEquals("Size:3", threadSpin.toString());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes four thread objects is functioning.
     */
    @Test
    public void testThreadSpinner4(){
        ThreadSpinner threadSpin = new ThreadSpinner(new Thread(), new Thread(), new Thread(), new Thread());
        assertEquals("Size:4", threadSpin.toString());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes five thread objects is functioning.
     */
    @Test
    public void testThreadSpinner5(){
        ThreadSpinner threadSpin = new ThreadSpinner(new Thread(), new Thread(), new Thread(), new Thread(), new Thread());
        assertEquals("Size:5", threadSpin.toString());
    }

    /***
     * Tests that the ThreadSpinner constructor that takes ten thread objects is functioning.
     */
    @Test
    public void testThreadSpinner10(){
        ThreadSpinner threadSpin = new ThreadSpinner(new Thread(), new Thread(), new Thread(), new Thread(), new Thread(), new Thread(), new Thread(), new Thread(), new Thread(), new Thread());
        assertEquals("Size:10", threadSpin.toString());
    }



}