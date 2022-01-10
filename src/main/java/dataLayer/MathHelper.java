package dataLayer;



import models.Strain;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

import java.util.ArrayList;

public abstract class MathHelper {
    
    public static double SpearmansCorrelation(ArrayList<Strain> strainsList)
    {
        int len = strainsList.size();
        double[] xArray = new double[len];
        double[] yArray = new double[len];

        for(int i=0;i<strainsList.size();i++){
                xArray[i]= strainsList.get(i).getWaarde();
                yArray[i]= strainsList.get(i).getAge();
            }

        SpearmansCorrelation corr = new SpearmansCorrelation();
        return corr.correlation(xArray,yArray);
    }
}
