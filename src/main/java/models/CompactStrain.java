package models;

import dataLayer.ReadCompactMeteo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CompactStrain {
    private LocalDate date;
    private float average;
    private float max;
    private static List<CompactMeteo> meteos = ReadCompactMeteo.getMeteo();
    private final static LocalDate beginDate = LocalDate.of(2019, 11, 27);


    public CompactStrain(LocalDate date, float average, float max) {
        this.date = date;
        this.average = average;
        this.max = max;
    }

    public CompactMeteo getMeteo() {
        for (CompactMeteo meteo : this.meteos) {
            if (meteo.getDate().isEqual(this.date)) return meteo;
        }
        return null;
    }

    public int getAge() {
        // https://stackoverflow.com/questions/20165564/calculating-days-between-two-dates-with-java
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
