public class ThreadSaver {

    private double lower, upper; // thread lower and upper bounds/range, decimal
    private String name; // thread name
    private int[] intArray;
    private long begin, end; // begin and end times
    private int arraySum; // The sum of the array contents that were specified by the bounds/range.
    private boolean isRunning = true;

    /***
     * Default constructor for testing purposes.
     */
    public ThreadSaver(){
        // default constructor for testing purposes
    }

    /***
     * Constructor for a ThreadSaver object.
     * @param lower Lower bound of the range, between 0-1 & less than Upper.
     * @param upper Upper bound of the range, between 0-1 & greater than Lower.
     * @param name Name of the ThreadSaver, for example: "Quarter1".
     * @param intArray It requires the array.
     */
    public ThreadSaver(double lower, double upper, String name, int[] intArray) {
        this.intArray = intArray;
        this.setLower(lower);
        this.setUpper(upper);
        this.name = name;
        // this.begin = System.nanoTime(); // Is this when the thread begins?
    }

    /***
     * This method returns the value of the isRunning boolean.
     * @return
     */
    public boolean getIsRunning() {
        return isRunning;
    }

    /***
     * This method is how the thread running state can be changed.
     * @param running Requires a boolean parameter (true/false).
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /***
     * This returns the lower bound value.
     * @return This returns the lower bound value.
     */
    public double getLower() {
        return lower;
    }

    /***
     * This sets the lower bound value. The parameter must be between 0-1.
     * @param lower Requires a parameter between 0-1.
     */
    public void setLower(double lower) {
        this.lower = (int)(intArray.length * lower);
    }

    /***
     * This returns the upper bound.
     * @return This returns the upper bound.
     */
    public double getUpper() {
        return upper;
    }

    /***
     * This sets the upper bound value. The parameter must be between 0-1.
     * @param upper Requires a parameter between 0-1.
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
