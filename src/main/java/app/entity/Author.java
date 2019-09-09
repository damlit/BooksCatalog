package app.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Author implements Comparable<Author> {

    private String name;
    private Double averageRating;

    @JsonIgnore
    private double sumOfRating;

    @JsonIgnore
    private int ammountOfAddition;

    public Author(String name) {
        this.name = name;
        sumOfRating = 0.0;
        ammountOfAddition = 0;
        averageRating = 0.0;
    }

    public void calculateAverageRating() {
        if (sumOfRating != 0.0) {
            averageRating = sumOfRating / ammountOfAddition;
        }
    }

    public void addToRating(Double rating) {
        ammountOfAddition++;
        sumOfRating = sumOfRating + rating;
    }

    @JsonGetter("author")
    public String getName() {
        return name;
    }

    @JsonGetter("AverageRating")
    public Double getAverageRating() {
        return averageRating;
    }

    public int compareTo(Author authorToCompare) {
        return this.getAverageRating().compareTo(authorToCompare.getAverageRating());
    }
}