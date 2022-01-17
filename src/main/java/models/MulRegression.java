package models;

import java.util.ArrayList;


import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class MulRegression {
    ArrayList<CompactStrain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();
    double[] y= new double[getStrainList().size()];
    double[][] x = new double[getStrainList().size()][getStrainList().size()];

    public MulRegression(ArrayList<CompactStrain> strainList, Boolean wind, Boolean temp, Boolean rain){
        this.strainList = strainList;
        addInf(Influences.age,0);
        if (wind){
            addInf(Influences.windSpeed,1);
        }
        if (temp){
            addInf(Influences.windSpeed,2);
        }
        if (rain){
            addInf(Influences.windSpeed,3);
        }
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
                getYList()[i]=strain.getAge();
            }
            i++;
            }

        reg.newSampleData(getYList(),getXList());
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

    public double getY(int x){
        reg.
    }
}
