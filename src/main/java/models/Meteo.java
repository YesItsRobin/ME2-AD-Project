package models;

import java.time.LocalDateTime;

public class Meteo {
    private LocalDateTime date;
    private float temp;
    private float windsnelheid;
    private float windrichting;
    private float luchvochtigheid;
    private float luchtdruk;
    private float neerslag;
    private float zonneschijn;

    public Meteo(LocalDateTime date, float temp, float windsnelheid, float windrichting, float luchvochtigheid, float luchtdruk, float neerslag, float zonneschijn) {
        this.date = date;
        this.temp = temp;
        this.windsnelheid = windsnelheid;
        this.windrichting = windrichting;
        this.luchvochtigheid = luchvochtigheid;
        this.luchtdruk = luchtdruk;
        this.neerslag = neerslag;
        this.zonneschijn = zonneschijn;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    public void setDateTime(LocalDateTime date) {
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
                "dateTime=" + date +
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


