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

   // static contact contact = new contact("test", "test");

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

            //Files.write(filepath, contacts);

            //            adds line to the list.
//            Files.write(
//                    Paths.get("data", "contacts.txt"),
//                    Arrays.asList("info: " + contact.getName() + " | " + contact.getPhoneNumber()),
//                    StandardOpenOption.APPEND
//            );
            int response = 0;

           while(response != 5){
               System.out.println();
               System.out.println("1. View Contacts.");
               System.out.println("2. Add a new contact.");
               System.out.println("3. Search a contact by name.");
               System.out.println("4. Delete an existing contact.");
               System.out.println("5. Exit");
               System.out.println("Enter an option (1, 2, 3, 4 or 5)");
               response = sc.nextInt();
               sc.nextLine();

               switch (response){
                   case 1:
                       System.out.printf("%-20s | %-20s%n", "Name", "Phone Number");
                       System.out.println("------------------------------------");
                       for(String line : Files.readAllLines(filepath)){
                           String[] eachLine = line.split("-");
                           System.out.printf("%-20s | %-20s%n", eachLine[0], eachLine[1]);
                       }
                       break;
                   case 2:
                       System.out.println("Please enter the name:");
                       String name = sc.nextLine();
                       System.out.println("Please enter the phone number:");
                       String phone = sc.nextLine();
                       contact user = new contact(name, phone);

                       Files.write(Paths.get("data", "contacts.txt"), Arrays.asList(user.allInfo()), StandardOpenOption.APPEND);
                       break;
                   case 3:
                       System.out.println("Enter the name of the contact you want to look:");
                       String nameLook = sc.nextLine();
                       for(String line : Files.readAllLines(filepath)){
                           String[] eachLine = line.split("-");
                           if(eachLine[0].equalsIgnoreCase(nameLook)){
                               System.out.println(line);
                           }
//                           System.out.println(eachLine[0]);
//                           System.out.println(eachLine[1]);
                       }
                   case 4:
                       System.out.println("Enter the name to delete:");
                       String nameDelete = sc.nextLine();
                        List<String> tempList = new ArrayList<>();
                        for(String line : Files.readAllLines(filepath)){
                           String[] eachLine = line.split("-");
                           if(eachLine[0].equalsIgnoreCase(nameDelete)){
                               continue;
                           }
                           tempList.add(line);
                       }

                        Files.write(filepath, tempList);
               }
           }



        } catch (IOException x) {
            x.printStackTrace();
        }

    }

}
