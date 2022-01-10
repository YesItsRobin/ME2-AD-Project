package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Strain {
    private LocalDateTime dateTime;
    private String sensorName;
    private float waarde;
    private String unit;
    private Brugdeel brugdeel; //because there are only two options (vast:true and draai: false) we can use a boolean
    private float kopAfstand;
    private String element; //all attributes given in the .csv files
    int age;

    private final LocalDate beginDate = LocalDate.of(2019,11,27);

    //Empty constructor
    public Strain(){}

    //Overloaded constructor
    public Strain(LocalDateTime dateTime, String sensorName, int waarde, String unit, Brugdeel brugdeel, float kopAfstand, String element) {
        this.dateTime = dateTime;
        this.sensorName = sensorName;
        this.waarde = waarde;
        this.unit = unit;
        this.brugdeel = brugdeel;
        this.kopAfstand = kopAfstand;
        this.element = element;
        this.age = Period.between(beginDate, LocalDate.from(dateTime)).getDays() ;
    }


    @Override
    public String toString() {
        return "Strain{" +
                "dateTime=" + dateTime +
                ", sensorName='" + sensorName + '\'' +
                ", waarde=" + waarde +
                ", unit='" + unit + '\'' +
                ", brugdeel=" + brugdeel +
                ", kopAfstand=" + kopAfstand +
                ", element='" + element + '\'' +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public float getWaarde() {
        return waarde;
    }

    public void setWaarde(float waarde) {
        this.waarde = waarde;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Brugdeel getBrugdeel() {
        return brugdeel;
    }

    public void setBrugdeel(Brugdeel brugdeel) {
        this.brugdeel = brugdeel;
    }

    public float getKopAfstand() {
        return kopAfstand;
    }

    public void setKopAfstand(float kopAfstand) {
        this.kopAfstand = kopAfstand;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
