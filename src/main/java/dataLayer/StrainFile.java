//The Strain class, this is to get a path easier, we can put more info here later if necessary
package dataLayer;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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

        // i think this can be deleted!?
        //a switch to cover all groups. It's faster than a lot of if statements. Google "switch java" for more info.
        //the names of the csv's are constructed in a way the number starts at a set point and then increases by one.
        //Check the data files
//        this.number= switch (group) {   //changing the number to a strain number. the input is something like 2 or 6 but the names of the csv's are more like 20504
//            case 1 -> 10105 + number;   //case 1 here means: if(group==1)
//            case 2 -> 10200 + number;
//            case 3 -> 10300 + number;
//            case 4 -> 20400 + number;
//            case 5 -> 20500 + number;
//            case 6 -> 20600 + number;
//            case 7 -> 20703 + number;
//            case 8 -> 20800 + number;
//            default -> throw new IllegalStateException("Group does not exist: " + group); //if all fails, the group number doesn't exist
//        };
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
