package compactors;

import com.opencsv.CSVWriter;
import dataLayer.ReadMeteo;
import models.Meteo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TempMeteoCompacter {
    //do not run again!!!
    public static void compactCSVFile() throws IOException {

        List<Meteo> meteos = ReadMeteo.getMeteo(); //collects ALL data
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"dateTime", "temp", "windsnelheid", "windrichting", "luchvochtigheid", "luchtdruk", "neerslag", "zonneschijn"});

        LocalDateTime beginDate = meteos.get(0).getDateTime(); //gets date from first datapoint
        LocalDateTime currentDate = beginDate.withHour(0).withMinute(0).withSecond(0); //uses start of beginDate

        //initializes all variables
        float counter = 0; //to keep track of the amount of data summed up
        float sumTemp = 0;
        float sumWindsnelheid = 0;
        float sumWindrichting = 0;
        float sumLuchvochtigheid = 0;
        float sumLuchtdruk = 0;
        float sumNeerslag = 0;
        float sumZonneschijn = 0;

        for (Meteo meteo : meteos) {
            if (meteo.getDateTime().isBefore(currentDate.plusDays(1))) { //checks if the strain is still current date

                sumTemp += meteo.getTemp();
                sumWindrichting += meteo.getWindrichting();
                sumWindsnelheid += meteo.getWindsnelheid();
                sumLuchvochtigheid += meteo.getLuchvochtigheid();
                sumLuchtdruk += meteo.getLuchtdruk();
                sumNeerslag += meteo.getNeerslag();
                sumZonneschijn += meteo.getZonneschijn();
                counter++;

            } else { // if not current date
                // fills in a new datapoint of one day with ("dateTime", "temp", "windsnelheid", "windrichting", "luchvochtigheid", "luchtdruk", "neerslag", "zonneschijn")
                data.add(new String[]{String.valueOf(currentDate), String.valueOf(sumTemp / counter), String.valueOf(sumWindrichting / counter), String.valueOf(sumWindsnelheid / counter), String.valueOf(sumLuchvochtigheid / counter), String.valueOf(sumLuchtdruk / counter), String.valueOf(sumNeerslag / counter), String.valueOf(sumZonneschijn / counter)});
                currentDate = currentDate.plusDays(1); //goes to the next day
                //begin next day
                sumTemp = meteo.getTemp();
                sumWindrichting = meteo.getWindrichting();
                sumWindsnelheid = meteo.getWindsnelheid();
                sumLuchvochtigheid = meteo.getLuchvochtigheid();
                sumLuchtdruk = meteo.getLuchtdruk();
                sumNeerslag = meteo.getNeerslag();
                sumZonneschijn = meteo.getZonneschijn();
                counter = 1;

            }
        }
        //create new file in directory
        File file = new File("SensordataBridgeProjectApplicationDevelopment\\meteo\\meteoCompact.csv");
        file.createNewFile();

        // https://mkyong.com/java/how-to-export-data-to-csv-file-java/
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(data);
        }
    }
}

