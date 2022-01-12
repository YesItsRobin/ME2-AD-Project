package models;

import java.util.ArrayList;


import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;


public class MulRegression {
    ArrayList<CompactStrain> strainList;
    OLSMultipleLinearRegression reg= new OLSMultipleLinearRegression();

    public MulRegression(ArrayList<CompactStrain> strainList, Influences inf1, Influences inf2){
        this.strainList = strainList;
        //Build(inf1,inf2);

    }

    public MulRegression(ArrayList<CompactStrain> strainList, Influences inf1, Influences inf2, Influences inf3){
        this.strainList = strainList;
    }

    //private void Build(Influences inf1, Influences inf2){
    //    double[] y= new double[getStrainList().size()];
    //    double[][] x = new double[getStrainList().size()][getStrainList().size()];
    //    int i =0;
    //    for(CompactStrain strain : getStrainList()){
    //        y[i]=strain.getAge();
    //        x[i][0]=strain.getAverage();
    //        if (inf2 ==Influences.rainfall) {
    //           x[i][1] = strain.getMeteo().getNeerslag();
     //       }
     //       else if (inf2 ==Influences.temp){
     //           x[i][1] = strain.getMeteo().getTemp();
     //       }
     //       else if (inf2 ==Influences.windSpeed){
     //           x[i][1] = strain.getMeteo().getWindsnelheid();
     //       }
     //       }
//
     //   reg.newSampleData(y,x);
    //}



    public ArrayList<CompactStrain> getStrainList() {
        return strainList;
    }

    public OLSMultipleLinearRegression getReg() {
        return reg;
    }
}
