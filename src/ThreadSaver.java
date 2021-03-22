public class ThreadSaver {

    private double lower, upper; // thread lower and upper bounds/range, decimal
    private String name; // thread name
    private int[] intArray;
    private long begin, end; // begin and end times
    private int arraySum; // The sum of the array contents that were specified by the bounds/range.
    private boolean isRunning = true;

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    /***
     *
     * @return
     */
    public double getLower() {
        return lower;
    }

    /***
     *
     * @param lower
     */
    public void setLower(double lower) {
        this.lower = (int)(intArray.length * lower);
    }

    /***
     *
     * @return
     */
    public double getUpper() {
        return upper;
    }

    /***
     *
     * @param upper
     */
    public void setUpper(double upper) {
        this.upper = (int)(intArray.length * upper);
    }

    /***
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return
     */
    public int[] getIntArray() {
        return intArray;
    }

    /***
     *
     * @param intArray
     */
    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    /***
     *
     * @return
     */
    public long getBegin() {
        return begin;
    }

    /***
     *
     * @param begin
     */
    public void setBegin(long begin) {
        this.begin = begin;
    }

    /***
     *
     * @return
     */
    public long getEnd() {
        return end;
    }

    /***
     *
     * @param end
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /***
     *
     * @return
     */
    public int getArraySum() {
        return arraySum;
    }

    /***
     *
     * @param arraySum
     */
    public void setArraySum(int arraySum) {
        this.arraySum = arraySum;
    }

}
