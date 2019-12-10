import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class contactApp {

    final static String folder = "data";
    final static String fileName = "contacts.txt";
    final static Path dataDirectory = Paths.get(folder);
    final static Path dataFile = Paths.get(folder, fileName);
    final static Path filepath = Paths.get("data", "contacts.txt");


    //    make the list
    static List<String> contacts = new ArrayList<>();

//    Scanner
    static Scanner sc = new Scanner(System.in);

    public static String inputPhone(){
        System.out.println("Please enter a phone number:");
        String phone = sc.nextLine();

        try{
            Long.valueOf(phone);

        }catch (Exception e){
            System.out.println("Please enter a valid phone number...");
            return inputPhone();
        }

        if(phone.length() == 10 || phone.length() == 7){
            return phone;
        }else{
            System.out.println("Enter a 10 or 7 digit phone number..");
            return inputPhone();
        }
    };

    public static void displayContacts(){

        System.out.printf("%-20s | %-20s%n", "Name", "Phone Number");
        System.out.println("------------------------------------");
        try{
            for(String line : Files.readAllLines(filepath)){
                String[] eachLine = line.split("-");
                String phone = "";
                if(eachLine[1].length() == 10){
                    phone = eachLine[1].substring(0,3) + "-" + eachLine[1].substring(3,6) + "-" + eachLine[1].substring(6,10);
                }else if(eachLine[1].length() == 7){
                    phone = eachLine[1].substring(0,3) + "-" + eachLine[1].substring(3,7);
                }
                System.out.printf("%-20s | %-20s%n", eachLine[0], phone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void searchName(String name){
        try{
            for(String line : Files.readAllLines(filepath)){
                String[] eachLine = line.split("-");
                if(eachLine[0].equalsIgnoreCase(name)){
                    System.out.printf("%-20s | %-20s%n", eachLine[0], eachLine[1]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static boolean checkName(String name){
        try{
            for(String line : Files.readAllLines(filepath)){
                String[] eachLine = line.split("-");
                if(eachLine[0].equalsIgnoreCase(name)){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean checkPhone(String phone){
        try{
            for(String line : Files.readAllLines(filepath)){
                String[] eachLine = line.split("-");
                if(eachLine[1].equalsIgnoreCase(phone)){
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }


    public static void addName(){
        System.out.println("Please enter the name:");
        String name = sc.nextLine();
        boolean match = checkName(name);
        if(match){
            searchName(name);
            System.out.println("We found a matching name on the list. Do you want to continue? (Y/N)");
            String response = sc.nextLine();
            if(response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")){
                addName();
            }

        }
        String phone = inputPhone();
        boolean matchPhone = checkPhone(phone);
        if(matchPhone){
            System.out.println("Same phone number on file.");
            phone = inputPhone();
        }

        contact user = new contact(name, phone);
        try{
            Files.write(Paths.get("data", "contacts.txt"), Arrays.asList(user.allInfo()), StandardOpenOption.APPEND);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteName(){
        System.out.println("Enter the name to delete:");
        String nameDelete = sc.nextLine();
        List<String> tempList = new ArrayList<>();
        int counter = 0;
        try{
            for(String line : Files.readAllLines(filepath)){
                String[] eachLine = line.split("-");
                if(eachLine[0].equalsIgnoreCase(nameDelete)){
                    counter++;
                }
                //editList.add(line);
            }

            if(counter == 1){
                for(String line : Files.readAllLines(filepath)){
                    String[] eachLine = line.split("-");
                    if(eachLine[0].equalsIgnoreCase(nameDelete)){
                        continue;
                    }
                    tempList.add(line);
                }
            }else if(counter > 1){
                System.out.println("There were multiple entries with the same name.");
                System.out.println("Please enter a phone number to delete:");
                String phoneDelete = sc.nextLine();
                boolean isThere = checkPhone(phoneDelete);

                if(isThere){
                    for(String line : Files.readAllLines(filepath)){
                        String[] eachLine = line.split("-");
                        if(eachLine[1].equalsIgnoreCase(phoneDelete)){
                            continue;
                        }
                        tempList.add(line);
                    }
                }else{
                    System.out.println("Phone not in file.");
                }

            }
            if(tempList.size() > 1){
                Files.write(filepath, tempList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void editName() {
        System.out.println("Enter the name to edit:");
        String contactEdit = sc.nextLine();
        List<String> tempList = new ArrayList<>();
        int editCounter = 0;
        try {
            for (String line : Files.readAllLines(filepath)) {
                String[] eachLine = line.split("-");
                if (eachLine[0].equalsIgnoreCase(contactEdit)) {
                    editCounter++;
                }
                //editList.add(line);
            }

            if (editCounter == 1) {
                for (String line : Files.readAllLines(filepath)) {
                    String[] eachLine = line.split("-");
                    if (eachLine[0].equalsIgnoreCase(contactEdit)) {
                        continue;
                    }
                    tempList.add(line);
                }
            } else if (editCounter > 1) {
                System.out.println("There were multiple entries with the same name.");
                System.out.println("Please enter a phone number to edit:");
                String phoneDelete = sc.nextLine();
                boolean isThere = checkPhone(phoneDelete);

                if (isThere) {
                    for (String line : Files.readAllLines(filepath)) {
                        String[] eachLine = line.split("-");
                        if (eachLine[1].equalsIgnoreCase(phoneDelete)) {
                            continue;
                        }
                        tempList.add(line);
                    }
                } else {
                    System.out.println("Phone not in file.");
                }

            }
            if (tempList.size() > 1) {
                Files.write(filepath, tempList);
            }
            addName();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//        try{
//            for(String line : Files.readAllLines(filepath)){
//                String[] eachLine = line.split("-");
//                if(eachLine[0].equalsIgnoreCase(contactEdit)){
//                    continue;
//                }
//                editList.add(line);
//            }
//
//            Files.write(filepath, editList);
//            addName();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    public static void main(String[] args) {

        //        Creates the path

        //        creates file and directory if one or both dont already exist.
        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        } catch (IOException x) {
            x.printStackTrace();
        }

        int response = 0;

        while(response != 6){
            System.out.println();
            System.out.println("1. View Contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Edit a contact.");
            System.out.println("5. Delete an existing contact.");
            System.out.println("6. Exit");
            System.out.println("Enter an option (1, 2, 3, 4, 5 or 6)");
            response = sc.nextInt();
            sc.nextLine();

            switch (response){
                   case 1:
                       displayContacts();
                       break;
                   case 2:
                       addName();
                       break;
                   case 3:
                       System.out.println("Enter the name of the contact you want to look:");
                       String nameLook = sc.nextLine();
                       searchName(nameLook);
                       break;
                   case 4:
                       editName();
                       break;
                   case 5:
                       deleteName();
                       break;
            }
        }



    }

}

