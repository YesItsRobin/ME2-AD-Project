package dataLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//An abstract class that makes it possible to read any given csv
public abstract class BaseReader {
    public ArrayList<String> readCSV(String path) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(path));    //Opens the file
        String row = csvReader.readLine();          //reads the first row and puts it in the row variable
        ArrayList<String> data = new ArrayList<String>();//Creates an ArrayList to fill it with the data
        while (row != null) {   //This loop stops when it reaches the end of the file, then row will be null
            data.add(row);      //adds the row with data to the data ArrayList
            row = csvReader.readLine(); //reads the next row of data
        }
        return data;    //returns an arraylist with all rows of data, this is ugly since it is not split yet, it will look like "column1;column2;column3;..."
    }
}
