import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String[] args)
    {
        String id = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String csvPersonRec = "";
        int yearOfBirth = 0;
        boolean done = false;
        Scanner input = new Scanner(System.in);
        ArrayList<String> csvPerson = new ArrayList<>();

        do{
            id = SafeInput.getNonZeroLenString(input, "Enter ID: ");
            firstName = SafeInput.getNonZeroLenString(input, "Enter first name: ");
            lastName = SafeInput.getNonZeroLenString(input, "Enter last name: ");
            title = SafeInput.getNonZeroLenString(input, "Enter title: ");
            yearOfBirth = SafeInput.getRangedInt(input, "Enter your birth year: ", 1000, 9999);
            csvPersonRec = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth + "\n";
            csvPerson.add(csvPersonRec);
            done = SafeInput.getYNConfirm(input, "Are you finished?");
        }

        while(!done);
        for(String rec:csvPerson){
            System.out.println(rec);
        }
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : csvPerson)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
           System.out.println("Corrupt file");
            e.printStackTrace();
           System.exit(1);
        }

    }
}

