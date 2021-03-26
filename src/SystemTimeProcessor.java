/*
File: SystemTimeProcessor.java
Author: Ethan J. Nephew
Date due: March 28, 2021
Course: CEN-3024C
Description: This is a class that I wrote to help verify that the threads are operating correct. Ultimately, many of the methods in this class are not used in the main method.
*/

import java.util.Arrays;

/***
 * This class is for processing and calculating useful numbers as they pertain to multi-threading and nano-time. Some of the methods are pertinent to this assignment, but some are for calculating variables that are interesting to me.
 */
public class SystemTimeProcessor {

    private long averageBegin;
    private long averageEnd;
    private long averageRunTime;
    private long beginTimeVariation;
    private long endTimeVariation;
    private long runTime;

    /***
     * This returns the average begin.
     * @return This is the average system time that were recorded by multiple threads.
     */
    public long getAverageBegin() {
        return averageBegin;
    }

    /***
     * This sets the average begin time. This is a value that should only be set for testing purposes, otherwise it should be calculated.
     * @param averageBegin Long parameter that sets the average begin time.
     */
    public void setAverageBegin(long averageBegin) {
        this.averageBegin = averageBegin;
    }

    /***
     * This returns the average end time.
     * @return The average system end time that were recorded by multiple threads.
     */
    public long getAverageEnd() {
        return averageEnd;
    }

    /***
     * This sets the average end time. This is a value that should only be set for testing purposes, otherwise it should be calculated.
     * @param averageEnd Returns the average system end time of multple threads.
     */
    public void setAverageEnd(long averageEnd) {
        this.averageEnd = averageEnd;
    }

    /***
     * This returns the average runtime of multiple threads. This is the average amount of time that each thread took to finish their task.
     * @return Returns the average runtime of multiple threads.
     */
    public long getAverageRunTime() {
        return averageRunTime;
    }

    /***
     * Sets the averageRunTime variable. Useful for testing purposes.
     * @param averageRunTime Requires a long that will be assigned.
     */
    public void setAverageRunTime(long averageRunTime) {
        this.averageRunTime = averageRunTime;
    }

    /***
     * Returns the beginTimeVariation variable.
     * @return The long beginTimeVariation.
     */
    public long getBeginTimeVariation() {
        return beginTimeVariation;
    }

    /***
     * Assigns the parameter to the beginTimeVariation variable. Good for testing.
     * @param beginTimeVariation Requires a long data type.
     */
    public void setBeginTimeVariation(long beginTimeVariation) {
        this.beginTimeVariation = beginTimeVariation;
    }

    /***
     * Returns the endTimeVariation variable.
     * @return The long endTimeVariation.
     */
    public long getEndTimeVariation() {
        return endTimeVariation;
    }

    /***
     * Assigns the parameter to the endTimeVariation variable. Good for testing.
     * @param endTimeVariation Requires a long data type.
     */
    public void setEndTimeVariation(long endTimeVariation) {
        this.endTimeVariation = endTimeVariation;
    }

    /***
     * Returns the runTime variable.
     * @return The long runTime.
     */
    public long getRunTime() {
        return runTime;
    }

    /***
     * Assigns the parameter to the runTime variable.
     * @param runTime Requires a long data type.
     */
    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }
    /***
     * This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 10 threads.
     * @param ts0 Requires ThreadSaver0.
     * @param ts1 Requires ThreadSaver1.
     * @param ts2 Requires ThreadSaver2.
     * @param ts3 Requires ThreadSaver3.
     * @param ts4 Requires ThreadSaver4.
     * @param ts5 Requires ThreadSaver5.
     * @param ts6 Requires ThreadSaver6.
     * @param ts7 Requires ThreadSaver7.
     * @param ts8 Requires ThreadSaver8.
     * @param ts9 Requires ThreadSaver9.
     */
    public SystemTimeProcessor(ThreadSaver ts0, ThreadSaver ts1, ThreadSaver ts2, ThreadSaver ts3, ThreadSaver ts4, ThreadSaver ts5, ThreadSaver ts6, ThreadSaver ts7, ThreadSaver ts8, ThreadSaver ts9){
        long begin[] = {ts0.getBegin(),ts1.getBegin(),ts2.getBegin(),ts3.getBegin(), ts4.getBegin(), ts5.getBegin(), ts6.getBegin(), ts7.getBegin(), ts8.getBegin(), ts9.getBegin()};
        long end[] = {ts0.getEnd(), ts1.getEnd(), ts2.getEnd(), ts3.getEnd(), ts4.getEnd(), ts5.getEnd(), ts6.getEnd(), ts7.getEnd(), ts8.getEnd(), ts9.getEnd()};

        calcAVG(begin, 0);
        calcAVG(end, 1);
        calcAVGRuntime(begin, end);
        calcTimeVariationANDRuntime(begin, end);
    }

    /***
     * This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 5 threads.
     * @param ts0 Requires ThreadSaver0.
     * @param ts1 Requires ThreadSaver1.
     * @param ts2 Requires ThreadSaver2.
     * @param ts3 Requires ThreadSaver3.
     * @param ts4 Requires ThreadSaver4.
     */
    public SystemTimeProcessor(ThreadSaver ts0, ThreadSaver ts1, ThreadSaver ts2, ThreadSaver ts3, ThreadSaver ts4){
        long begin[] = {ts0.getBegin(),ts1.getBegin(),ts2.getBegin(),ts3.getBegin(),ts4.getBegin()};
        long end[] = {ts0.getEnd(), ts1.getEnd(), ts2.getEnd(), ts3.getEnd(),ts4.getEnd()};

        calcAVG(begin, 0);
        calcAVG(end, 1);
        calcAVGRuntime(begin, end);
        calcTimeVariationANDRuntime(begin, end);
    }

    /***
     * This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 4 threads.
     * @param ts0 Requires ThreadSaver0.
     * @param ts1 Requires ThreadSaver1.
     * @param ts2 Requires ThreadSaver2.
     * @param ts3 Requires ThreadSaver3.
     */
    public SystemTimeProcessor(ThreadSaver ts0, ThreadSaver ts1, ThreadSaver ts2, ThreadSaver ts3){
        long begin[] = {ts0.getBegin(),ts1.getBegin(),ts2.getBegin(),ts3.getBegin()};
        long end[] = {ts0.getEnd(), ts1.getEnd(), ts2.getEnd(), ts3.getEnd()};

        calcAVG(begin, 0);
        calcAVG(end, 1);
        calcAVGRuntime(begin, end);
        calcTimeVariationANDRuntime(begin, end);
    }

    /***
     * This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 3 threads.
     * @param ts0 Requires ThreadSaver0.
     * @param ts1 Requires ThreadSaver1.
     * @param ts2 Requires ThreadSaver2.
     */
    public SystemTimeProcessor(ThreadSaver ts0, ThreadSaver ts1, ThreadSaver ts2){
        long begin[] = {ts0.getBegin(), ts1.getBegin(), ts2.getBegin()};
        long end[] = {ts0.getEnd(), ts0.getEnd(), ts2.getEnd()};

        calcAVG(begin, 0);
        calcAVG(end, 1);
        calcAVGRuntime(begin, end);
        calcTimeVariationANDRuntime(begin, end);
    }

    /***
     * This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 2 threads.
     * @param ts0 Requires ThreadSaver0.
     * @param ts1 Requires ThreadSaver1.
     */
    public SystemTimeProcessor(ThreadSaver ts0, ThreadSaver ts1){
        long begin[] = {ts0.getBegin(), ts1.getBegin()};
        long end[] = {ts0.getEnd(), ts0.getEnd()};

        calcAVG(begin, 0);
        calcAVG(end, 1);
        calcAVGRuntime(begin, end);
        calcTimeVariationANDRuntime(begin, end);
    }

    /***
     This is a SystemTimeProcessing constructor. It calculates useful runtimes information between 2 threads.
     * @param ts Requires a single ThreadSaver.
     */
    public SystemTimeProcessor(ThreadSaver ts){
        long begin = ts.getBegin();
        long end = ts.getEnd();
        calcTimes(begin, end);
    }

    /***
     * Default constructor for the SystemTimeProcessor class. This is useful for testing purposes.
     */
    public SystemTimeProcessor(){
        // default constructor for testing purposes
    }

    /***
     * This method is used to calculate the runtime of a single thread.
     * @param begin Begin time, long data type.
     * @param end End time, long data type.
     */
    public void calcTimes(long begin, long end){
        runTime = end - begin;
    }

    /***
     * This method calculates the average of a long[] array and saves them in the location specified by the control.
     * @param array long[] array.
     * @param control 0 calculates the average begin time, 1 calculates the average end time.
     */
    public void calcAVG(long[] array, int control){
        long total = 0;
        for(int i = 0; i < array.length; i++){
            total = total + array[i];
        }
        if (control==0)
            averageBegin = total/array.length;
        if (control==1)
            averageEnd = total/array.length;
    }

    /**
     * This takes two long[] arrays of equal size and calculates the average of both arrays, and then calculates the difference between those averages.
     * @param begin The first long[] array.
     * @param end The second long[] array.
     */
    public void calcAVGRuntime(long[] begin, long[] end){
        long beginLong = 0;
        long endLong = 0;
        if(begin.length == end.length) { // they must be the same size
            for (int i = 0; i < begin.length; i++){
                beginLong = beginLong + begin[i];
                endLong = endLong + end[i];
            }
            averageRunTime = (endLong/end.length) - (beginLong/begin.length);
        }
    }

    /***
     * This method is meant to help verify that threads are running in parallel. The closer the time variations are to each other the closer together the threads ran.
     * @param begin The array that contains the begin times.
     * @param end The array that contains the end times.
     */
    public void calcTimeVariationANDRuntime(long[] begin, long[] end){
        Arrays.sort(begin);
        Arrays.sort(end);
        beginTimeVariation = begin[begin.length - 1] - begin[0]; // this is the amount of time all threads took to begin
        endTimeVariation = end[end.length - 1] - end[0];
        runTime = end[end.length - 1] - begin[begin.length - 1];
    }
}
