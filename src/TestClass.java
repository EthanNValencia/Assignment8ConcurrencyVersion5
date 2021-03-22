import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    ThreadSaver ts = new ThreadSaver();

    @Test
    public void testUpperSet(){
        ts.setUpper(0.25);
        assertEquals(0.25, ts.getUpper());
    }
    @Test
    public void testUpperGetZERO(){
        assertEquals(0.0, ts.getUpper());
    }
    @Test
    public void testLowerSet(){
        ts.setLower(0.5);
        assertEquals(0.5, ts.getLower());
    }
    @Test
    public void testLowerGetZERO(){
        assertEquals(0, ts.getLower());
    }
    @Test
    public void testNameSet(){
        ts.setName("TestName");
        assertEquals("TestName", ts.getName());
    }
    @Test
    public void testNameGetNULL(){
        assertEquals(null, ts.getName());
    }
    @Test
    public void testSetIntArray(){
        int[] array = {0,1,2,3,4};
        ts.setIntArray(array);
        for(int i = 0; i < array.length; i++){
            assertEquals(i, array[i]); // 0==0, 1==1, 2==2, 3==3, 4==4
        }
    }
    @Test
    public void testGetIntArrayNULL(){
        assertEquals(null, ts.getIntArray());
    }
    @Test
    public void testGetIntArrayNOTNULL(){
        int[] array = {0,1,2,3,4};
        ts.setIntArray(array);
        assertNotEquals(null, ts.getIntArray());
    }
}