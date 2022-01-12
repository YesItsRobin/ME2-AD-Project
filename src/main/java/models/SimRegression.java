package models;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class SimRegression {

    ArrayList<Strain> strainList;
    Influences inf;
    SimpleRegression reg = new SimpleRegression();

    public SimRegression(ArrayList<Strain> strainList, Influences inf){
        this.inf = inf;
        this.strainList = strainList;

        Build();
    }

    public void Build(){
        for(Strain strain : getStrainList()){
            if (getInf()==Influences.age) {
                getReg().addData(strain.getWaarde(), strain.getAge());
            }

        }
    }



    public ArrayList<Strain> getStrainList() {
        return strainList;
    }

    public Influences getInf() {
        return inf;
    }

    public SimpleRegression getReg() {
        return reg;
    }
}
