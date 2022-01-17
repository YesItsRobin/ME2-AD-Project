package models;

import java.util.ArrayList;


import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class MulRegression {
    ArrayList<CompactStrain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();
    double[] y;
    double[][] x;
    double[] beta;

    public MulRegression(ArrayList<CompactStrain> strainList, Boolean wind, Boolean temp, Boolean rain){
        this.strainList = strainList;
        ArrayList<Influences> infs = new ArrayList<>();
        infs.add(Influences.age);
        if (wind){
            infs.add(Influences.windSpeed);
        }
        if (temp){
            infs.add(Influences.temp);
        }
        if (rain){
            infs.add(Influences.rainfall);
        }
        y = new double[getStrainList().size()];
        x = new double[infs.size()][getStrainList().size()];

        int index = 0;
        for (Influences inf:infs){
            addInf(inf,index);
            index++;
        }
        addInf(Influences.average,999);
        reg.    newSampleData(getYList(),getXList());
        this.beta = reg.estimateRegressionParameters();
    }

    private void addInf(Influences inf, int index){
        int i =0;
        for(CompactStrain strain : getStrainList()){
            if (inf ==Influences.rainfall) {
                x[index][i] = strain.getMeteo().getNeerslag();
            }
            else if (inf ==Influences.temp){
                x[index][i] = strain.getMeteo().getTemp();
            }
            else if (inf ==Influences.windSpeed){
                x[index][i] = strain.getMeteo().getWindsnelheid();
            }
            else if (inf ==Influences.average){
                getYList()[i]=strain.getAverage();
            }
            else if (inf == Influences.age){
                x[index][i]=strain.getAge();
            }
            i++;
            }
    }



    public ArrayList<CompactStrain> getStrainList() {
        return strainList;
    }

    public OLSMultipleLinearRegression getReg() {
        return reg;
    }

    public double[] getYList() {
        return y;
    }

    public double[][] getXList() {
        return x;
    }

    public double[] getBeta() {
        return beta;
    }

    public double getY(ArrayList<Double> x){
        double y = 0;
        y+=beta[0];
        for (int i=1;i<getBeta().length;i++){
            y+=getBeta()[i]* x.get(i);
        }
        return y;
    }
}
