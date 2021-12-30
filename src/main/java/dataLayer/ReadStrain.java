package dataLayer;

import models.Strain;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadStrain extends BaseReader {

    public ArrayList<Strain> getStrains(int group, int number) throws IOException {
        ArrayList<String> data = readCSV(group, number);
        ArrayList<Strain> strains = new ArrayList<Strain>();
        for (String row : data) {
            strains.add(buildStrain(row));
        }
        return strains;
    }

    private static Strain buildStrain(String row) {

        ArrayList<String> dataSplit = new ArrayList<>(List.of(row.split(";"))); //split data in diff parts
        boolean brugdeelBoolean = switch (dataSplit.get(4)) { //gives the attribute brugdeel a t or f, if not draai or vast it throws an exception
            case "draai" -> false;
            case "vast" -> true;
            default -> throw new IllegalStateException("Unexpected value: " + dataSplit.get(4));
        };

        //initializes the attributes and do the needed parses and replacements
        LocalDateTime dateTime = LocalDateTime.parse(dataSplit.get(0).replace("Z", ""));
        String sensorName = dataSplit.get(1);
        float waarde;
        try {
            waarde = Float.parseFloat(dataSplit.get(2).replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println(dataSplit.get(2));
            waarde = 0;
        }
        String unit = dataSplit.get(3);
        float kopAfstand = Float.parseFloat(dataSplit.get(5).replace(",", "."));
        String element = dataSplit.get(6); //all attributes given in the .csv files

        return new Strain(dateTime, sensorName, waarde, unit, brugdeelBoolean, kopAfstand, element);
    }

    public static List<Strain> strainsFromTimeframe(LocalDateTime begin, LocalDateTime end) throws IOException {
        List<Strain> strainsDate = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1)).list().length; j++) {
                System.out.println("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1) + (j + 1));
                boolean dataExtracted = false;
                boolean dataFound = false;
                int index = findApproximationIndex(i + 1, j + 1, begin);
                try {
                    while (!dataExtracted) {
                        Strain strain = buildStrain(readCSVRow(i + 1, j + 1, index));
                        boolean statement = strain.getDateTime().isAfter(begin) && strain.getDateTime().isBefore(end);

                        if (statement) {
                            strainsDate.add(strain);
                            dataFound=true;
                        }
                        if ((!statement) && dataFound) {
                            dataExtracted = true;
                        }
                        index+=100;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println(strainsDate);
            }
        }
        return strainsDate;
    }

    public static int findApproximationIndex(int group, int number, LocalDateTime begin) throws IOException {
        int index = 0;
        boolean done = false;
        while (!done) {
            Strain strain = buildStrain(readCSVRow(group, number, index));
            if (strain.getDateTime().compareTo(begin)<0){index+=100000;}
            else if (strain.getDateTime().compareTo(begin)>=0){index-=100000; done=true;}
        }

        done = false;
        while (!done) {
            Strain strain = buildStrain(readCSVRow(group, number, index));
            if (strain.getDateTime().compareTo(begin)<0){index+=10000;}
            else if (strain.getDateTime().compareTo(begin)>=0){index-=10000; done=true;}
            }

        done = false;
        while (!done) {
            Strain strain = buildStrain(readCSVRow(group, number, index));
            if (strain.getDateTime().compareTo(begin)<0){index+=1000;}
            else if (strain.getDateTime().compareTo(begin)>=0){index-=1000; done=true;}
        }

        done = false;
        while (!done) {
            Strain strain = buildStrain(readCSVRow(group, number, index));
            if (strain.getDateTime().compareTo(begin)<0){index+=100;}
            else if (strain.getDateTime().compareTo(begin)>=0){index-=100; done=true;}
        }

        return index;
    }
}

