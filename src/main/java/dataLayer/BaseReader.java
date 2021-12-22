package dataLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//An abstract class that makes it possible to read any given csv. !!Right now only strainfiles!! This still needs to be fixed if we want to read other kinds of files
public abstract class BaseReader {
    public ArrayList<String> readCSV(int group, int number) throws IOException {

        StrainFile testStrain = new StrainFile(group, number);  //Uses the StrainFile class to get acces to that group and number's csv
        BufferedReader csvReader = new BufferedReader(new FileReader(testStrain.getPath()));    //Opens the file
        String row = csvReader.readLine();          //reads the first row and puts it in the row variable
        ArrayList<String> data = new ArrayList<String>();//Creates an ArrayList to fill it with the data
        while (row != null) {   //This loop stops when it reaches the end of the file, then row will be null
            data.add(row);      //adds the row with data to the data ArrayList
            row = csvReader.readLine(); //reads the next row of data
        }
        return data;    //returns an arraylist with all rows of data, this is ugly since it is not split yet, it will look like "column1;column2;column3;..."
    }
}
