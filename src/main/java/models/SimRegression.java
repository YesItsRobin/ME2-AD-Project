package models;

import dataLayer.ReadCompactMeteo;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class SimRegression {

    private final Influences inf;
    SimpleRegression reg = new SimpleRegression();
    int climate;

    public SimRegression(ArrayList<CompactStrain> strainList, Influences inf, int climate){
        this.inf = inf;
        this.climate = climate;
        BuildS(strainList);
    }

    public SimRegression(Influences inf, int years, int climate){
        this.inf = inf;
        this.climate = climate;
        ArrayList<CompactMeteo> data = ReadCompactMeteo.getMeteo();
        BuildM(data);
        double dataOfYesterday = getY(365);
        double dataOfToday =0;
        for (int i=1;i<=years;i++) {
            for (int j = 1; j <= 365; j++) {
                int age = 368 + j + ((i - 1) * 365);
                if (inf == Influences.temp) {
                    dataOfToday=(3.7 * climate + 2.3)/64/365 + dataOfYesterday;
                }
                else if (inf == Influences.windSpeed){
                    dataOfToday=(0.081 * climate + 0.95)/64/365 + dataOfYesterday;
                }
                reg.addData(age, dataOfToday);
            }
        }
    }

    public void BuildM(ArrayList<CompactMeteo> data){
        for (CompactMeteo meteo : data){
            if (getInf()==Influences.temp){
                reg.addData(meteo.getAge(),meteo.getTemp());
            }
            else if (getInf()==Influences.windSpeed){
                reg.addData(meteo.getAge(),meteo.getWindsnelheid());
            }
            else if(getInf()==Influences.atmosPres){
                reg.addData(meteo.getAge(),meteo.getLuchtdruk());
            }
            else if(getInf()==Influences.sun){
                reg.addData(meteo.getAge(),meteo.getZonneschijn());
            }
            else if(getInf() == Influences.humidity){
                reg.addData(meteo.getAge(), meteo.getLuchvochtigheid());
            }
        }
    }

    public void BuildS(ArrayList<CompactStrain> strainList){
        for(CompactStrain strain : strainList){
            if (getInf()==Influences.age) {
                getReg().addData(strain.getAge(), strain.getAverage());
            }
            else if (getInf()==Influences.windSpeed){
                getReg().addData(strain.getAge(),strain.getMeteo().getWindsnelheid());
            }
            else if (getInf()==Influences.temp){
                getReg().addData(strain.getAge(),strain.getMeteo().getTemp());
            }
            else if(getInf()==Influences.atmosPres){
                getReg().addData(strain.getAge(),strain.getMeteo().getLuchtdruk());
            }
            else if(getInf()==Influences.sun){
                getReg().addData(strain.getAge(),strain.getMeteo().getZonneschijn());
            }
            else if(getInf() ==Influences.humidity){
                getReg().addData(strain.getAge(),strain.getMeteo().getLuchvochtigheid());
            }
        }

    }

    public Double getY(double x){
        return getReg().predict(x);
    }

    public SimpleRegression getReg() {
        return reg;
    }

    public Influences getInf() {
        return inf;
    }
}
