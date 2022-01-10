package dataLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//An abstract class that makes it possible to read any given csv
public abstract class BaseReader {
    public static ArrayList<String> readCSV(String path) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));    //Opens the file
        String row = csvReader.readLine();          //reads the first row and puts it in the row variable
        ArrayList<String> data = new ArrayList<String>();//Creates an ArrayList to fill it with the data
        while (row != null) {   //This loop stops when it reaches the end of the file, then row will be null
            data.add(row);      //adds the row with data to the data ArrayList
            row = csvReader.readLine(); //reads the next row of data
        }
        return data;    //returns an arraylist with all rows of data, this is ugly since it is not split yet, it will look like "column1;column2;column3;..."
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
