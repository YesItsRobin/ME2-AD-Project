package dataLayer;

import models.Meteo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static dataLayer.BaseReader.readCSV;

public class ReadMeteo {

    public static ArrayList<Meteo> getMeteo() throws IOException {
        ArrayList<String> data = readCSV("SensordataBridgeProjectApplicationDevelopment\\meteo\\meteo.csv");       //Calls the BaseReader, gets an ugly ArrayList back
        ArrayList<Meteo> meteoData = new ArrayList<>();    //Creates an empty arraylist of Strains
        data.remove(0); //removes variable names
        data.remove(0); // and units
        for (String row : data) {
            if (!(row.contains(";;"))) { //the rows with ";;" in them have no values, so it skips them
                meteoData.add(buildMeteoData(row));
            }
        }
        return meteoData;
    }

    //Turns an ugly String row into a usable Meteo
    private static Meteo buildMeteoData(String row) {
        ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(";"))); //splits data and puts it in an arraylist

        //initializes the attributes and do the needed parses and replacements

        LocalDateTime dateTime = LocalDateTime.parse(dataSplit.get(0).replace("Z", ""));
        float temp = Float.parseFloat(dataSplit.get(1).replace(",", "."));
        float windsnelheid = Float.parseFloat(dataSplit.get(2).replace(",", "."));
        float windrichting = Float.parseFloat(dataSplit.get(3).replace(",", "."));
        float luchvochtigheid = Float.parseFloat(dataSplit.get(4).replace(",", "."));
        float luchtdruk = Float.parseFloat(dataSplit.get(5).replace(",", "."));
        float neerslag = Float.parseFloat(dataSplit.get(6).replace(",", "."));
        float zonneschijn = Float.parseFloat(dataSplit.get(7).replace(",", "."));

        return new Meteo(dateTime, temp, windsnelheid, windrichting, luchvochtigheid, luchtdruk, neerslag, zonneschijn);
    }
}
