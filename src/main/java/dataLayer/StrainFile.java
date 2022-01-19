//The Strain class, this is to get a path easier, we can put more info here later if necessary
package dataLayer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StrainFile {
    public int group;   //We have 8 groups. Look in the data files
    public int number;  //The number here relates to the csv in the group. This varies from group to group.
    // So perhaps we have to adjust some code to know how many csv's are im a group
    public String path; //This is a string containing the path from the repository. If this doesn't work, we will have to find an alt.
    public boolean compact;

    public StrainFile(int group, int number, boolean compact) {   //The constructor accepts group and number, the path will be created inside the constructor
        //starts a list of all the filenames in a group
        List<String> groupFile = List.of(new File("SensordataBridgeProjectApplicationDevelopment\\strain-group" + group+"Compact").list());
        String numberString = groupFile.get(number - 1); //gets the right file name
        for (String s : Arrays.asList("strain#", ".csv")) { //strips away unnecessary info
            numberString = numberString.replace(s, "");
        }
        this.number = Integer.valueOf(numberString); //parses it as an int

        this.group = group;
        this.path = "SensordataBridgeProjectApplicationDevelopment\\strain-group" + this.group;
        if (compact) this.path += "Compact";
        this.path += "\\strain#" + this.number + ".csv";
    }

    public StrainFile(int group, int number) {
        new StrainFile(group, number, false);
    }

    public int getGroup() {
        return group;
    }

    public int getNumber() {
        return number;
    }

    public String getPath() {
        return path;
    }

}
