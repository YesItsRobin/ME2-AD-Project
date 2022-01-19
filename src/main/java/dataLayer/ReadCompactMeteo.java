package dataLayer;

import models.CompactMeteo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static dataLayer.BaseReader.readCSV;

public class ReadCompactMeteo {

    public static ArrayList<CompactMeteo> getMeteo() {
        ArrayList<String> data = null;       //Calls the BaseReader, gets an ugly ArrayList back
        try {
            //reads whole file
            data = readCSV("SensordataBridgeProjectApplicationDevelopment\\meteo\\meteoCompact.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<CompactMeteo> meteoData = new ArrayList<>();    //Creates an empty arraylist of Strains
        data.remove(0); //removes first line with variable names
        for (String row : data) {
            meteoData.add(buildMeteoData(row));
        }
        return meteoData;
    }

    //Turns an ugly String row into a usable Meteo
    private static CompactMeteo buildMeteoData(String row) {
        row = row.replace("\"", "");
        ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(","))); //splits data and puts it in an arraylist

        //initializes the attributes and do the needed parses and replacements

        LocalDate date = LocalDate.parse(dataSplit.get(0).replace("T00:00", ""));
        float temp = Float.parseFloat(dataSplit.get(1));
        float windsnelheid = Float.parseFloat(dataSplit.get(2));
        float luchvochtigheid = Float.parseFloat(dataSplit.get(4));

        if (dataSplit.get(3).contains("null")) return new CompactMeteo(date, temp, windsnelheid, luchvochtigheid);

        float windrichting = Float.parseFloat(dataSplit.get(3));
        float luchtdruk = Float.parseFloat(dataSplit.get(5));
        float neerslag = Float.parseFloat(dataSplit.get(6));
        float zonneschijn = Float.parseFloat(dataSplit.get(7));

        return new CompactMeteo(date, temp, windsnelheid, windrichting, luchvochtigheid, luchtdruk, neerslag, zonneschijn);
    }
}
