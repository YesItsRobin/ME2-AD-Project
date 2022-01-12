package dataLayer;

import models.Brugdeel;
import models.CompactStrain;
import models.Strain;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadCompactStrain extends BaseReader{
    //getStrains returns a pretty Arraylist of Strains of a given group and number
    public static ArrayList<CompactStrain> getStrains(int group, int number) throws IOException {
        StrainFile file = new StrainFile(group, number, true);        //Uses the StrainFile class to get access to that group and number's csv
        ArrayList<String> data = readCSV(file.getPath());       //Calls the BaseReader, gets an ugly ArrayList back
        ArrayList<CompactStrain> strains = new ArrayList<>();    //Creates an empty arraylist of Strains
        for (String row : data) {
            strains.add(buildStrain(row));
        }
        return strains;
    }

    //Turns an ugly String row into a usable Strain
    private static CompactStrain buildStrain(String row) {
        row = row.replace("\"", "");
        ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(","))); //splits data and puts it in an arraylist

        //initializes the attributes and do the needed parses

        LocalDate date = LocalDate.parse(dataSplit.get(0).replace("T00:00", ""));
        float average = Float.parseFloat(dataSplit.get(1));
        float max = Float.parseFloat(dataSplit.get(2));

        return new CompactStrain(date, average, max);
    }
}
