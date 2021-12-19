package dataLayer;

import java.io.BufferedReader;
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
            data.add(row);
            row = csvReader.readLine();
        }
        return data;
    }
}
