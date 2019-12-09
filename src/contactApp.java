import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class contactApp {

    final static String folder = "data";
    final static String fileName = "contacts.txt";

    static List<String> contacts = Arrays.asList();

    public static void main(String[] args) {

        Path dataDirectory = Paths.get(folder);
        Path dataFile = Paths.get(folder, fileName);

        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

            Path filepath = Paths.get("data", "contacts.txt");
            Files.write(filepath, contacts);


        } catch (IOException x) {
            x.printStackTrace();
        }

    }

}
