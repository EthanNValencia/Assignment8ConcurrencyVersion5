/*
File: GenerateJava.java
Author: Ethan J. Nephew
Date due: March 28, 2021
Course: CEN-3024C
Description: I used this method to generate some of the code for a test. I do not feel like deleting it.
*/

import java.math.BigDecimal;
import java.math.RoundingMode;

/***
 * Class that was used to Java code.
 */
public class GenerateJava {
    /***
     * I didn't want to have to write all the code for the fifty thread test. I decided it would be better for me to write code that generates code that I can copy and paste.
     */
    public static void generateJavaForThe50Threads(){
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

}
