package dataLayer;

import models.Brugdeel;
import models.Strain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadStrain extends BaseReader{

    //getStrains returns a pretty Arraylist of Strains of a given group and number
    public ArrayList<Strain> getStrains(int group, int number) throws IOException {
        StrainFile file = new StrainFile(group, number);        //Uses the StrainFile class to get access to that group and number's csv
        ArrayList<String> data = readCSV(file.getPath());       //Calls the BaseReader, gets an ugly ArrayList back
        ArrayList<Strain> strains = new ArrayList<Strain>();    //Creates an empty arraylist of Strains
        for (String row: data) {
            strains.add(buildStrain(row));  //Adds the row as a Strain, check the buildStrain method
        }
        return strains;
    }

    //Turns an ugly String row into a usable Strain
    private Strain buildStrain(String row){
            ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(";"))); //split data and put it in an arraylist

            //initializes the attributes and do the needed parses and replacements
            Brugdeel brugdeel = switch (dataSplit.get(4)) { //gives the attribute brugdeel a t or f, if not draai or vast it throws an exception
                case "draai" -> Brugdeel.draai;
                case "vast"  -> Brugdeel.vast;
                default -> throw new IllegalStateException("Unexpected value: " + dataSplit.get(4));
            };

            LocalDateTime dateTime = LocalDateTime.parse(dataSplit.get(0).replace("Z", ""));
            String sensorName = dataSplit.get(1);
            int waarde = Integer.parseInt(dataSplit.get(2).replace(",", ""));
            String unit = dataSplit.get(3);
            float kopAfstand = Float.parseFloat(dataSplit.get(5).replace(",", "."));
            String element = dataSplit.get(6); //all attributes given in the .csv files

            return new Strain(dateTime,sensorName, waarde, unit, brugdeel, kopAfstand, element);
    }


}
