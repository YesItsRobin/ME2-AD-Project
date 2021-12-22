package dataLayer;

import models.Strain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadStrain extends BaseReader{

    public ArrayList<Strain> getStrains(int group, int number) throws IOException {
        ArrayList<String> data = readCSV(group,number);
        ArrayList<Strain> strains = new ArrayList<Strain>();
        data.remove(0);
        for (String row: data) {
            strains.add(buildStrain(row));
        }
        return strains;
    }

    private Strain buildStrain(String row){

            ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(";"))); //split data in diff parts
            boolean brugdeelBoolean = switch (dataSplit.get(4)) { //gives the attribute brugdeel a t or f, if not draai or vast it throws an exception
                case "draai" -> false;
                case "vast" -> true;
                default -> throw new IllegalStateException("Unexpected value: " + dataSplit.get(4));
            };

            //initializes the attributes and do the needed parses and replacements
            LocalDateTime dateTime = LocalDateTime.parse(dataSplit.get(0).replace("Z", ""));
            String sensorName = dataSplit.get(1);
            int waarde;
    try {
        waarde = Integer.parseInt(dataSplit.get(2).replace(",", ""));
    }
    catch (NumberFormatException e){
        waarde = 0;
    }
            String unit = dataSplit.get(3);
            float kopAfstand = Float.parseFloat(dataSplit.get(5).replace(",", "."));
            String element = dataSplit.get(6); //all attributes given in the .csv files

            return new Strain(dateTime,sensorName, waarde, unit, brugdeelBoolean, kopAfstand, element);
    }


}
