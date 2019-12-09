import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class contactApp {

    final static String folder = "data";
    final static String fileName = "contacts.txt";

    //    make the list
    static List<String> contacts = new ArrayList<>();

    static contact contact = new contact("test", "test");

//    Scanner
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //        Creates the path
        Path dataDirectory = Paths.get(folder);
        Path dataFile = Paths.get(folder, fileName);

        //        creates file and directory if one or both dont already exist.
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

            Path filepath = Paths.get("data", "contacts.txt");

            Files.write(filepath, contacts);

            //            adds line to the list.
            Files.write(
                    Paths.get("data", "contacts.txt"),
                    Arrays.asList("info: " + contact.getName() + " | " + contact.getPhoneNumber()),
                    StandardOpenOption.APPEND
            );



        } catch (IOException x) {
            x.printStackTrace();
        }

    }

}
