package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CompactStrain {
    private LocalDate date;
    private float average;
    private float max;
    private final static LocalDate beginDate = LocalDate.of(2019, 11, 27);


    public CompactStrain(LocalDate date, float average, float max) {
        this.date = date;
        this.average = average;
        this.max = max;
    }

    public int getAge() {
        return (int) ChronoUnit.DAYS.between(beginDate, this.date);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "CompactStrain{" +
                "date=" + date +
                ", average=" + average +
                ", max=" + max +
                '}';
    }
}
