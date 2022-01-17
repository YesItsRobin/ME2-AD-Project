package models;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class SimRegression {

    private final Influences inf;
    ArrayList<CompactStrain> strainList;
    SimpleRegression reg = new SimpleRegression();

    public SimRegression(ArrayList<CompactStrain> strainList, Influences inf){
        this.strainList = strainList;
        this.inf = inf;
        Build();
    }

    public void Build(){
        for(CompactStrain strain : getStrainList()){
            if (getInf()==Influences.age) {
                getReg().addData(strain.getAge(), strain.getAverage());
            }
            else if (getInf()==Influences.windSpeed){
                getReg().addData(strain.getMeteo().getWindsnelheid(), strain.getAverage());
            }
            else if (getInf()==Influences.temp){
                getReg().addData(strain.getMeteo().getTemp(), strain.getAverage());
            }
        }

    }

    public Double getY(double x){
        return getReg().predict(x);
    }

    public ArrayList<CompactStrain> getStrainList() {
        return strainList;
    }

    public SimpleRegression getReg() {
        return reg;
    }

    public Influences getInf() {
        return inf;
    }
}
