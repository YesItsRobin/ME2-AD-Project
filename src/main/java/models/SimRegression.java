package models;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class SimRegression {

    ArrayList<CompactStrain> strainList;
    Influences inf;
    SimpleRegression reg = new SimpleRegression();

    public SimRegression(ArrayList<CompactStrain> strainList, Influences inf){
        this.inf = inf;
        this.strainList = strainList;
        Build();
    }

    public void Build(){
        for(CompactStrain strain : getStrainList()){
            if (getInf()==Influences.age) {
                getReg().addData(strain.getAverage(), strain.getAge());
            }
        }
    }

    public Double getY(double x){
        return getReg().predict(x);
    }

    public ArrayList<CompactStrain> getStrainList() {
        return strainList;
    }

    public Influences getInf() {
        return inf;
    }

    public SimpleRegression getReg() {
        return reg;
    }
}
