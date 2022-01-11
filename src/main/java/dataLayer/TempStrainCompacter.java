package dataLayer;

import com.opencsv.CSVWriter;
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
        for (int i = 3; i < 8; i++) {
            List<Object> groupFile = List.of(new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1)).list());
            for (int j = 0; j < groupFile.size(); j++) {
                int count = j;
                List<Strain> strains;
                while (true) {
                    try {
                        strains = ReadStrain.getStrains(i + 1, count + 1);
                        break;
                    } catch (Exception e) {
                        count++;
                    }
                }
                List<String[]> data = new ArrayList<>();

                LocalDateTime beginDate = strains.get(0).getDateTime();
                LocalDateTime currentDate = beginDate.withHour(0).withMinute(0).withSecond(0);

                float counter = 0;
                float sumStrain = 0;
                float maxStrain = 0;
                for (Strain strain : strains) {
                    if (Float.NaN==strain.getWaarde()){
                        System.out.println(strain);
                        continue;
                    }

                    else if (strain.getDateTime().isBefore(currentDate.plusDays(1))) {
                        if (Math.abs(strain.getWaarde()) > Math.abs(maxStrain)) {
                            maxStrain = strain.getWaarde();
                        }
                        sumStrain += strain.getWaarde();
                        counter++;

                    } else {
                        data.add(new String[]{String.valueOf(currentDate), String.valueOf(sumStrain / counter), String.valueOf(maxStrain)});
                        currentDate = currentDate.plusDays(1);
                        sumStrain = strain.getWaarde();
                        maxStrain = strain.getWaarde();
                        counter = 1;

                    }
                }
                File file = new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1) + "Compact\\" + groupFile.get(j));
                file.createNewFile();

                //        https://mkyong.com/java/how-to-export-data-to-csv-file-java/
                try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                    writer.writeAll(data);
                }
            }
        }
    }
}