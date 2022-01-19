package models;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;


public class MulRegression {
    ArrayList<CompactStrain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();
    double[] y;
    double[][] x;
    double[] beta;

    public MulRegression(ArrayList<CompactStrain> strainList, Boolean wind, Boolean temp, Boolean atmosPres,
                                                                Boolean sun,  Boolean humidity){
        this.strainList = strainList;
        ArrayList<Influences> infs = new ArrayList<>();
        infs.add(Influences.age);
        if (wind){
            infs.add(Influences.windSpeed);
        }
        if (temp){
            infs.add(Influences.temp);
        }
        if (atmosPres){
            infs.add(Influences.atmosPres);
        }
        if (sun){
            infs.add(Influences.sun);
        }
        if (humidity){
            infs.add(Influences.humidity);
        }
        y = new double[getStrainList().size()];
        x = new double[getStrainList().size()][infs.size()];

        int index = 0;
        for (Influences inf:infs){
            addInf(inf,index);
            index++;
        }
        addInf(Influences.average,999);
        reg.newSampleData(getYList(),getXList());
        this.beta = reg.estimateRegressionParameters();
    }

    private void addInf(Influences inf, int index){
        int i =0;
        for(CompactStrain strain : getStrainList()){
            if (inf ==Influences.temp){
                x[i][index] = strain.getMeteo().getTemp();
            }
            else if (inf ==Influences.windSpeed){
                x[i][index] = strain.getMeteo().getWindsnelheid();
            }
            else if (inf == Influences.atmosPres){
                x[i][index] = strain.getMeteo().getLuchtdruk();
            }
            else if (inf == Influences.sun){
                x[i][index] = strain.getMeteo().getZonneschijn();
            }
            else if (inf == Influences.humidity){
                x[i][index] = strain.getMeteo().getLuchvochtigheid();
            }
            else if (inf ==Influences.average){
                getYList()[i]=strain.getAverage();
            }
            else if (inf == Influences.age){
                x[i][index]=strain.getAge();
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

    public double getY(ArrayList<Double> xx){
        double y = 0;
        y+=getBeta()[0];

        for (int i=1;i<getBeta().length;i++){
            y+=getBeta()[i]* xx.get(i-1);
        }
        return y;
    }
}