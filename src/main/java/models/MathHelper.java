package models;

import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.ArrayList;

public abstract class MathHelper {

    public static double SpearmansCorrelation(ArrayList<CompactStrain> strainsList, Influences inf)
    {
        int len = strainsList.size();
        double[] xArray = new double[len];
        double[] yArray = new double[len];

        for(int i=0;i<strainsList.size();i++){
            xArray[i]= strainsList.get(i).getAverage();

            switch (inf) {
                case age -> yArray[i] = strainsList.get(i).getAge();
                case windSpeed -> yArray[i] = strainsList.get(i).getMeteo().getWindsnelheid();
                case temp -> yArray[i] = strainsList.get(i).getMeteo().getTemp();
                case atmosPres -> yArray[i] = strainsList.get(i).getMeteo().getLuchtdruk();
                case sun -> yArray[i] = strainsList.get(i).getMeteo().getZonneschijn();
                case humidity -> yArray[i] = strainsList.get(i).getMeteo().getLuchvochtigheid();
            }
        }

        SpearmansCorrelation corr = new SpearmansCorrelation();

        return corr.correlation(xArray,yArray);

    }
}
