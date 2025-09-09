import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;
public class ProductWriter {
    public static void main(String[] args) {
        String id = "";
        String name = "";
        String description = "";
        String csvProductRec = "";
        int yearOfBirth = 0;
        double cost;
        boolean done = false;
        Scanner input = new Scanner(System.in);
        ArrayList<String> csvProducts = new ArrayList<>();
        do {
            id = SafeInput.getNonZeroLenString(input, "Enter ID");
            name = SafeInput.getNonZeroLenString(input, "Enter name");
            description = SafeInput.getNonZeroLenString(input, "Enter description");
            cost = SafeInput.getDouble(input, "Enter cost");
            csvProductRec = id + ", " + name + ", " + description + ", " + cost + "\n";
            csvProducts.add(csvProductRec);
            done = SafeInput.getYNConfirm(input, "Are you finished?");
        }
        while (!done);
        for (String rec : csvProducts) {
            System.out.println(rec);
        }
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");
        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String rec : csvProducts) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Corrupt file");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
