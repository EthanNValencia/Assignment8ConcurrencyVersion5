import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    ThreadSaver ts = new ThreadSaver();
    ArrayGenerator ag = new ArrayGenerator();

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

}