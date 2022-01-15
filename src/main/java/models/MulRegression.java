package models;

import java.util.ArrayList;


import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class MulRegression {
    ArrayList<CompactStrain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();
    double[] y= new double[getStrainList().size()];
    double[][] x = new double[getStrainList().size()][getStrainList().size()];

    public MulRegression(ArrayList<CompactStrain> strainList, Influences inf1, Influences inf2, Influences inf3){
        this.strainList = strainList;
        addInf(Influences.age,0);
        addInf(inf1, 1);
        addInf(inf2, 2);
        addInf(inf3, 3);
    }

    public MulRegression(ArrayList<CompactStrain> strainList, Influences inf1, Influences inf2){
        this.strainList = strainList;
        int i=0;
        for(CompactStrain strain : getStrainList()){
            getY()[i]=strain.getAge();
            i++;
        }
        addInf(Influences.age,0);
        addInf(inf1, 1);
        addInf(inf2, 2);
    }

    public MulRegression(ArrayList<CompactStrain> strainList, Influences inf1){
        this.strainList = strainList;
        int i=0;
        for(CompactStrain strain : getStrainList()){
            getY()[i]=strain.getAge();
            i++;
        }
        addInf(Influences.age,0);
        addInf(inf1, 1);
    }

    private void addInf(Influences inf, int index){
        int i =0;
        for(CompactStrain strain : getStrainList()){
            x[0][i]=strain.getAverage();
            if (inf ==Influences.rainfall) {
                x[index][i] = strain.getMeteo().getNeerslag();
            }
            else if (inf ==Influences.temp){
                x[index][i] = strain.getMeteo().getTemp();
            }
            else if (inf ==Influences.windSpeed){
                x[index][i] = strain.getMeteo().getWindsnelheid();
            }
            else if (inf ==Influences.age){
                getY()[i]=strain.getAge();
            }
            i++;
            }

        reg.newSampleData(getY(),getX());
    }



    public ArrayList<CompactStrain> getStrainList() {
        return strainList;
    }

    public OLSMultipleLinearRegression getReg() {
        return reg;
    }

    public double[] getY() {
        return y;
    }

    public double[][] getX() {
        return x;
    }
}
