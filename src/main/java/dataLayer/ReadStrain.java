package dataLayer;

import models.Brugdeel;
import models.Strain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadStrain extends BaseReader {

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

            return new Strain(dateTime,sensorName, waarde, unit, brugdeel, kopAfstand, element);
            }
            
    public static List<Strain> strainsFromTimeframe(LocalDateTime begin, LocalDateTime end) throws IOException {
        List<Strain> strainsDate = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1)).list().length; j++) {
                System.out.println("SensordataBridgeProjectApplicationDevelopment\\strain-group" + (i + 1) + (j + 1));
                boolean dataExtracted = false;
                boolean dataFound = false;
                int index = findIndex(i + 1, j + 1, begin);
                BufferedReader csvReader = getBufferedReader(i + 1, j + 1, index);
                try {
                    while (!dataExtracted) {
                        Strain strain = buildStrain(csvReader.readLine());
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
    

    

    public static int findIndex(int group, int number, LocalDateTime begin) throws IOException {
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

        done = false;
        while (!done) {
            Strain strain = buildStrain(readCSVRow(group, number, index));
            if (strain.getDateTime().compareTo(begin)<0){index+=1;}
            else if (strain.getDateTime().compareTo(begin)>=0){done=true;}
        }

        return index;
    }
}

