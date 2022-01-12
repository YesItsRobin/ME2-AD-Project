package models;

import java.util.ArrayList;


import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class MulRegression {
    ArrayList<Strain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();

    public MulRegression(ArrayList<Strain> strainList, Influences inf1, Influences inf2){
        this.strainList = strainList;

    }

    public MulRegression(ArrayList<Strain> strainList, Influences inf1, Influences inf2, Influences inf3){
        this.strainList = strainList;
        OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();
    }




    public ArrayList<Strain> getStrainList() {
        return strainList;
    }

    public OLSMultipleLinearRegression getReg() {
        return reg;
    }
}
