package dataLayer;

import models.Brugdeel;
import models.Strain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadStrain extends BaseReader {


    //getStrains returns a pretty Arraylist of Strains of a given group and number
    public static ArrayList<Strain> getStrains(int group, int number) throws IOException {
        StrainFile file = new StrainFile(group, number);        //Uses the StrainFile class to get access to that group and number's csv
        ArrayList<String> data = readCSV(file.getPath());       //Calls the BaseReader, gets an ugly ArrayList back
        ArrayList<Strain> strains = new ArrayList<Strain>();    //Creates an empty arraylist of Strains
        data.remove(0);
        for (String row : data) {
            Strain tempStrain = buildStrain(row); // check the buildStrain method
            if (tempStrain.getWaarde() < 0 || tempStrain.getWaarde() >= 0)
                strains.add(tempStrain);  //Adds the row as a Strain after checking if waarde has a number as a value check the buildStrain method
        }
        return  strains;
    }

    //Turns an ugly String row into a usable Strain
    private static Strain buildStrain(String row) {
        ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(";"))); //splits data and puts it in an arraylist

        //initializes the attributes and do the needed parses and replacements
        Brugdeel brugdeel = switch (dataSplit.get(4)) { //gives the attribute brugdeel a t or f, if not draai or vast it throws an exception
            case "draai" -> Brugdeel.draai;
            case "vast" -> Brugdeel.vast;
            default -> throw new IllegalStateException("Unexpected value: " + dataSplit.get(4));
        };

        LocalDateTime dateTime = LocalDateTime.parse(dataSplit.get(0).replace("Z", ""));
        String sensorName = dataSplit.get(1);
        float waarde = Float.parseFloat(dataSplit.get(2).replace(",", "."));
        String unit = dataSplit.get(3);
        float kopAfstand = Float.parseFloat(dataSplit.get(5).replace(",", "."));
        String element = dataSplit.get(6); //all attributes given in the .csv files

        return new Strain(dateTime, sensorName, waarde, unit, brugdeel, kopAfstand, element);
    }
}

