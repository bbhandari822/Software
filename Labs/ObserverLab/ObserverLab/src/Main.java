
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 11/28/17.
 */
public class Main {

    static int createCount = 0;
    static int listCount = 0;
    static int deleteCount = 0;
    static int modifyCount = 0;
    static HelperClass helperClass = new HelperClass();
    static ListFiles listFiles = new ListFiles(helperClass);
    static DeleteFiles deleteFiles = new DeleteFiles(helperClass);
    static CreateFiles createFiles = new CreateFiles(helperClass);
    static ModifyFiles modifyFiles = new ModifyFiles(helperClass);

    public static String filePath;

    public static void main(String[] args) {
        chooseFromFirstMenu();
        System.out.println();
        helperClass.notifyAllObservers();
    }

    private static void chooseFromFirstMenu() throws NullPointerException {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "1. List Files");
        map.put(1, "2. Quit");

            System.out.println("Please choose the following menu: ");
            for (int i = 0; i < map.size(); i++) {
                System.out.println(map.get(i));
            }

            Scanner keyboard = new Scanner(System.in);
            while (!keyboard.hasNextInt()) {
                System.out.println("Please use integer only");
                keyboard.nextLine();
            }
            int userInput = keyboard.nextInt();
            if(userInput <= 2){
                switch (userInput) {
                    case 1:
                        listCount++;
                        try {
                            listFiles.listallofTheFiles();
                            chooseFromDifferentMenu();
                        }catch (Exception exception){
                            System.out.println("DIR not valid");
                            chooseFromFirstMenu();
                        }
                        break;
                    case 2:
                        break;
                }
            }else{
                chooseFromFirstMenu();
            }

    }

    private static void chooseFromDifferentMenu() {

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "1. Delete File");
        hashMap.put(1, "2. Create File");
        hashMap.put(2, "3. Modify File by duplicating its contents and appending them to the end");
        hashMap.put(3, "4. Return to Previous Menu");

            System.out.println("Please choose the following menu: ");
            for (int i = 0; i < hashMap.size(); i++) {
                System.out.println(hashMap.get(i));
            }

            Scanner keyboard = new Scanner(System.in);
            while (!keyboard.hasNextInt()) {
                System.out.println("Please use integer only");
                keyboard.nextLine();
            }
            int userInput = keyboard.nextInt();
            if(userInput <= 4) {
                switch (userInput) {
                    case 1:
                        deleteCount++;
                        deleteFiles.deleteFilesInTheFolder();
                        chooseFromDifferentMenu();
                        break;
                    case 2:
                        createCount++;
                        try {
                            createFiles.createFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        chooseFromDifferentMenu();
                        break;
                    case 3:
                        modifyCount++;
                        try {
                            modifyFiles.modifyFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        chooseFromDifferentMenu();
                        break;
                    case 4:
                        chooseFromFirstMenu();
                        break;

                }

            }else{
                chooseFromDifferentMenu();
            }

    }

}



