package app.entity;

public class PairAuthorAndAverage {

    private final String authorName;
    private final Double average;

    public PairAuthorAndAverage(String authorName, Double average) {
        this.authorName = authorName;
        this.average = average;
    }

    public java.lang.String toString() {
        return "Pair[" + this.authorName + "," + this.average + "]";
    }

    public String getAuthorName() {
        return authorName;
    }

    public Double getAverage() {
        return average;
    }
}