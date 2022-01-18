package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CompactMeteo {
    private LocalDate date;
    private float temp;
    private float windsnelheid;
    private float windrichting;
    private float luchvochtigheid;
    private float luchtdruk;
    private float neerslag;
    private float zonneschijn;
    private final static LocalDate beginDate = LocalDate.of(2019, 11, 27);

    public CompactMeteo(LocalDate date, float temp, float windsnelheid, float windrichting, float luchvochtigheid, float luchtdruk, float neerslag, float zonneschijn) {
        this.date = date;
        this.temp = temp;
        this.windsnelheid = windsnelheid;
        this.windrichting = windrichting;
        this.luchvochtigheid = luchvochtigheid;
        this.luchtdruk = luchtdruk;
        this.neerslag = neerslag;
        this.zonneschijn = zonneschijn;
    }

    public CompactMeteo(LocalDate date, float temp, float windsnelheid, float luchvochtigheid) {
        this.date = date;
        this.temp = temp;
        this.windsnelheid = windsnelheid;
        this.luchvochtigheid = luchvochtigheid;
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

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getWindsnelheid() {
        return windsnelheid;
    }

    public void setWindsnelheid(float windsnelheid) {
        this.windsnelheid = windsnelheid;
    }

    public float getWindrichting() {
        return windrichting;
    }

    public void setWindrichting(float windrichting) {
        this.windrichting = windrichting;
    }

    public float getLuchvochtigheid() {
        return luchvochtigheid;
    }

    public void setLuchvochtigheid(float luchvochtigheid) {
        this.luchvochtigheid = luchvochtigheid;
    }

    public float getLuchtdruk() {
        return luchtdruk;
    }

    public void setLuchtdruk(float luchtdruk) {
        this.luchtdruk = luchtdruk;
    }

    public float getNeerslag() {
        return neerslag;
    }

    public void setNeerslag(float neerslag) {
        this.neerslag = neerslag;
    }

    public float getZonneschijn() {
        return zonneschijn;
    }

    public void setZonneschijn(float zonneschijn) {
        this.zonneschijn = zonneschijn;
    }

    @Override
    public String toString() {
        return "Meteo{" +
                "date=" + date +
                ", temp=" + temp +
                ", windsnelheid=" + windsnelheid +
                ", windrichting=" + windrichting +
                ", luchvochtigheid=" + luchvochtigheid +
                ", luchtdruk=" + luchtdruk +
                ", neerslag=" + neerslag +
                ", zonneschijn=" + zonneschijn +
                '}';
    }
}
