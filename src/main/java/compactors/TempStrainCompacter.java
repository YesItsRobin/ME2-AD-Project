package compactors;

import com.opencsv.CSVWriter;
import dataLayer.ReadStrain;
import models.Strain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TempStrainCompacter {
    //do not run again!!!
    public static void compactCSVFile() throws IOException {
        for (int i = 0; i < 8; i++) { //iterates over the 8 groups
            // looks at the number and name of the files in that group
            List<String> groupFile = List.of(new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1)).list());
            for (int j = 0; j < groupFile.size(); j++) {
                List<Strain> strains = ReadStrain.getStrains(i + 1, j + 1); //collects ALL data
                List<String[]> data = new ArrayList<>();

                LocalDateTime beginDate = strains.get(0).getDateTime(); //gets date from first datapoint
                LocalDateTime currentDate = beginDate.withHour(0).withMinute(0).withSecond(0); //uses start of beginDate

                float counter = 0;
                float sumStrain = 0;
                float maxStrain = 0;
                for (Strain strain : strains) {
                    if (strain.getDateTime().isBefore(currentDate.plusDays(1))) { //checks if the strain is still current date
                        if (Math.abs(strain.getWaarde()) > Math.abs(maxStrain)) { //updates maximum if it is higher than the current max
                            maxStrain = strain.getWaarde();
                        }
                        sumStrain += strain.getWaarde();
                        counter++;

                    } else { // if not current date
                        // fills in a new datapoint of one day with ("date","average","max")
                        data.add(new String[]{String.valueOf(currentDate), String.valueOf(sumStrain / counter), String.valueOf(maxStrain)});
                        currentDate = currentDate.plusDays(1); //goes to the next day
                        //begin next day
                        sumStrain = strain.getWaarde();
                        maxStrain = strain.getWaarde();
                        counter = 1;

                    }
                }
                //create new file in directory
                File file = new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1) + "Compact\\" + groupFile.get(j));
                file.createNewFile();

                // https://mkyong.com/java/how-to-export-data-to-csv-file-java/
                try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                    // write all the data in the csv file
                    writer.writeAll(data);
                }
            }
        }
    }
}