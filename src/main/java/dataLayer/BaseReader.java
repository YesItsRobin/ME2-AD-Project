package dataLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class BaseReader {
    public ArrayList<String> readCSV(int group, int number) throws IOException {
        StrainFile testStrain = new StrainFile(group, number);
        System.out.println(testStrain.getPath());

        BufferedReader csvReader = new BufferedReader(new FileReader(testStrain.getPath()));
        String row = csvReader.readLine();
        ArrayList<String> data = new ArrayList<String>();
        while (row != null) {
            row = csvReader.readLine();
            data.add(row);
        }
        return data;
    }

    public static String readCSVRow(int group, int number, int index) throws IOException {
        StrainFile testStrain = new StrainFile(group, number);
        BufferedReader csvReader = new BufferedReader(new FileReader(testStrain.getPath()));
        String row = csvReader.readLine();
        row = csvReader.readLine();

        for (int i = 0; i < index; i++) {
            row = csvReader.readLine();
        }

        return row;
    }

    public static BufferedReader getBufferedReader(int group, int number, int index) throws IOException{
        StrainFile testStrain = new StrainFile(group, number);
        BufferedReader csvReader = new BufferedReader(new FileReader(testStrain.getPath()));
        String row;
        row = csvReader.readLine();

        for (int i = 0; i < index; i++) {
            row = csvReader.readLine();
        }
        return csvReader;
    }

}
