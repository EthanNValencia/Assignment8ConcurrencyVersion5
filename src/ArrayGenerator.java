/*
File: ArrayGenerator.java
Author: Ethan J. Nephew
Date due: March 28, 2021
Course: CEN-3024C
Description: This class contains the necessary method for generating an int[] array of a specified size. All contents off the array will be between 1-10.
*/

import java.util.Random;

/***
 * This class will generate an int[] array of the user's specified size. Contents of the array will be randomly generated at a range of 1-10.
 */
public class ArrayGenerator {

    private int sum = 0;

    /***
     * Returns the contents of the int sum.
     * @return Returns the contents of the int sum.
     */
    public int getSum() {
        return sum;
    }

    /***
     * Setter for the int sum.
     * @param sum Requires the int that will be assigned to the sum.
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /***
     * This is a simple method that generates an nSize int[] array with contents that range from 1-10. In addition, it counts the content of the array and assigns the sum.
     * @param nSize This is the specified size of the array.
     * @return The method will return the fully generated array.
     */
    public int[] generateArray(int nSize){
        int list[] = new int[nSize];
        for (int i = 0; i < nSize; i++) {
            Random ran = new Random();
            int x = ran.nextInt(10) + 1;
            list[i] = x;
            sum = sum + x;
        }
        return list;
    }
}