import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class TwoDimensionalArrays {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private TwoDimensionalArrays() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        try {
            // Creating the writer
            final FileWriter myWriter = new FileWriter("output.csv");

            try {
                // Creating the input file objects
                final File students = new File("students.txt");
                final File assignments = new File("assignments.txt");

                // Creating the scanner.
                final Scanner scanner = new Scanner(students);
                final Scanner scanner2 = new Scanner(assignments);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    // getting next line and putting it in the interim list
                    line = scanner.nextLine();
                    lines.add(line);
                }
                // set a counter
                int counter = 0;
                // create an array to hold the data in the list
                final String[] nameArr = new String[lines.size()];
                // For loop takes the data in the list and places it into array
                for (String name : lines) {
                    nameArr[counter] = name;
                    counter++;
                }
                // resetting counter and interim list
                counter = 0;
                lines.clear();
                // getting data in the second file and inputting it to the
                // newly emptied list
                while (scanner2.hasNextLine()) {
                    line = scanner2.nextLine();
                    lines.add(line);
                }
                // creating a new array to hold this data
                final String[] assignArr = new String[lines.size()];
                // taking the data from the interim list and inputting it into
                // newly made array
                for (String assignment : lines) {
                    assignArr[counter] = assignment;
                    counter++;
                }
                // Calling the generate marks function
                final String[][] marks = generateMarks(nameArr, assignArr);
                // setting up what is displayed in the csv file
                for (String[] row : marks) {
                    // creating the builder
                    final StringBuilder builder = new StringBuilder();
                    // Iterating through rows and colums
                    for (int col = 0; col < row.length; col++) {
                        // adding to row
                        builder.append(row[col]);
                        // checks to see if its the last data point in the row.
                        if (col != row.length - 1) {
                            builder.append(", ");
                        } else {
                            builder.append("\n");
                        }
                    }

                    // writing to the csv file
                    myWriter.write(builder.toString());
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            myWriter.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method calculates the mean.
    *
    * @param names needed for the rows
    * @param assignments needed for the columns
    * @return the marks 2dArray
    */

    public static String[][] generateMarks(
        String[] names, String[] assignments) {
        // creating mean and standard Deviation
        final int mean = 75;
        final int stdDeviation = 10;
        // creating random object
        final Random random = new Random();
        // creating the 2D array
        final String[][] mks = new String[
            names.length + 1][assignments.length + 1];
        // Setting up the header for the first column
        mks[0][0] = "names";
        // Setting up the headers for the rest of the columns
        for (int counter = 1; counter < assignments.length + 1; counter++) {
            mks[0][counter] = assignments[counter - 1];
        }
        // Setting up the names/headers for the rows
        for (int i = 1; i < names.length + 1; i++) {
            mks[i][0] = names[i - 1];
        }
        // Randomly generating the data and inputting it into the 2D Array
        for (int row = 1; row < names.length + 1; row++) {
            for (int col = 1; col < assignments.length + 1; col++) {
                // This makes random numbers that correlate a mean and
                // standard deviation
                final int rNum = (int) (
                    random.nextGaussian() * stdDeviation + mean);
                mks[row][col] = String.valueOf(rNum);
            }
        }
        // Returning the 2D array to main
        return mks;
    }
}
