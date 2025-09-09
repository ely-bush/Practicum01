import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static  java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 5;
        String id, firstName, lastName, title;
        int yob;
        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while(reader.ready()){
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;
                    System.out.printf("\nLine %4d %60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nData file read.");
                String[] fields;
                for(String l:lines){
                    fields = l.split(",");
                    if(fields.length == FIELDS_LENGTH){
                        id        = fields[0].trim();
                        firstName = fields[1].trim();
                        lastName  = fields[2].trim();
                        title     = fields[3].trim();
                        yob       = Integer.parseInt(fields[4].trim());
                        System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yob);
                    }
                    else{
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                    }
                }
            else {
                System.out.println("Failure to choose a file to process");
                System.out.println("Please Run the program again.");
                System.exit(0);
            }
            }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        }

    }

