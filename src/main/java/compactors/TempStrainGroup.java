package compactors;

import com.opencsv.CSVWriter;
import dataLayer.ReadCompactStrain;
import models.CompactStrain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TempStrainGroup {
    public static void compactCSVFiles() throws IOException {
        for (int i = 0; i < 8; i++) {
            ArrayList<CompactStrain> strains = ReadCompactStrain.getStrainsGroup(i + 1);
            strains.sort(new Comparator<CompactStrain>() {
                @Override
                public int compare(CompactStrain strain1, CompactStrain strain2) {
                    return strain1.getDate().compareTo(strain2.getDate());
                }
            });


            List<String[]> data = new ArrayList<>();

            LocalDate currentDate = strains.get(0).getDate(); //gets date from first datapoint, uses start of beginDate

            float counter = 0;
            float sumStrain = 0;
            float maxStrain = 0;

            for (CompactStrain strain : strains) {
                if (strain.getDate().isBefore(currentDate.plusDays(1))) { //checks if the strain is still current date
                    if (Math.abs(strain.getMax()) > Math.abs(maxStrain)) { //updates maximum if it is higher than the current max
                        maxStrain = strain.getMax();
                    }
                    sumStrain += strain.getAverage();
                    counter++;

                } else { // if not current date
                    // fills in a new datapoint of one day with ("date","average","max")
                    data.add(new String[]{String.valueOf(currentDate), String.valueOf(sumStrain / counter), String.valueOf(maxStrain)});
                    currentDate = currentDate.plusDays(1); //goes to the next day
                    //begin next day
                    sumStrain = strain.getAverage();
                    maxStrain = strain.getMax();
                    counter = 1;

                }

                //create new file in directory
                File file = new File("SensordataBridgeProjectApplicationDevelopment\\StrainGroupCompact\\StrainGroup" + (i + 1) + ".csv");
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
