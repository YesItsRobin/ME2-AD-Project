package models;

import java.time.LocalDate;

public class CompactStrain {
    private LocalDate date;
    private float average;
    private float max;

    public CompactStrain(LocalDate date, float average, float max) {
        this.date = date;
        this.average = average;
        this.max = max;
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
