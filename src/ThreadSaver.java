public class ThreadSaver {

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getArraySum() {
        return arraySum;
    }

    public void setArraySum(int arraySum) {
        this.arraySum = arraySum;
    }

    private double lower, upper; // thread lower and upper bounds/range
    private String name; // thread name
    private int[] intArray;
    private long begin, end; // begin and end times
    private int arraySum; // The sum of the array contents that were specified by the bounds/range.

}
