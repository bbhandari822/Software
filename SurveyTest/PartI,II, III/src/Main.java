import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

/**
 * This call is used to initialize the whole survey/test generating system. It asks user input to choose either test or survey.
 * If it is either one of them, it will provide 5 different options to choose from. From there it will direct to Survey class.
 */

public class Main implements Serializable{

    private static LinkedHashMap<String, Survey> surveyIntheFolder = new LinkedHashMap<>();
    private static LinkedHashMap<String, Test> testInTheFolder = new LinkedHashMap<>();

    private static int grade;

    public static void main(String[] args) throws IOException {
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1,"1. Survey");
        hashMap.put(2,"2. Test");
        hashMap.put(3,"3. Exit");

        do{
            System.out.println("Please choose Survey or Test");
            for(int i = 1; i <= hashMap.size(); i++){
                System.out.println(hashMap.get(i));
            }

            Scanner keyboard = new Scanner(System.in);
            while(!keyboard.hasNextInt()) {
                System.out.println("Please enter integer only: ");
                keyboard.next();
            }
            int userChoice = keyboard.nextInt();

            if(userChoice == 1 ){
                handalingSurvey(new Survey());
            }else if(userChoice == 2) {
                handalingTest(new Test());
            }else if (userChoice == 3){
                System.exit(0);
            }else{
                System.out.println("Sorry no option found");
            }
        }while (true);
    }

    private static int getChosenSurvey() {

        if (surveyIntheFolder.size() == 0){
            System.out.println("Please load the survey first, there are no survey loaded to display");
            return 0;
        }
        ArrayList<String> keys = new ArrayList<>(surveyIntheFolder.keySet());
        for (int i = 0; i < surveyIntheFolder.size(); i++)
        {
            int a = i+1;
            String SurveyName = keys.get(i);
            System.out.println(a + ") " + SurveyName);
        }
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        return keyboard.nextInt();
    }

    private static int getChosenTest() {

        if (testInTheFolder.size() == 0){
            System.out.println("Please load the test first, there are no tests loaded to display");
            return 0;
        }
        ArrayList<String> keys = new ArrayList<>(testInTheFolder.keySet());
        for (int i = 0; i < testInTheFolder.size(); i++)
        {
            int a = i+1;
            String SurveyName = keys.get(i);
            System.out.println(a + ") " + SurveyName);
        }
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        return keyboard.nextInt();
    }

    public static File getSurveyToLoad(Survey survey) {
        File folder = new File(survey.getPathOfFile());
        if(folder.list().length == 0) {
            System.out.println("There are no survey in the folder!");
            return null;
        }
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            int a = i+1;
            if (listOfFiles[i].isFile()) {
                System.out.println(a + ") " + listOfFiles[i].getName());
            }
        }
        System.out.println("Enter file to load survey from");
        int number;
        do{
            System.out.print("Please enter valid number: ");
            Scanner keyboard = new Scanner(System.in);
            while(!keyboard.hasNextInt()) {
                System.out.println("Please enter integer only: ");
                keyboard.next();
            }
            number = keyboard.nextInt();
        } while (!(listOfFiles[number - 1].isFile()));
        return listOfFiles[number - 1];
    }

    private static void handalingTest(Test test) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "1. Create a new Test");
        map.put(2, "2. Display a Test");
        map.put(3, "3. Load a Test");
        map.put(4, "4. Save a Test");
        map.put(5, "5. Modify an Existing Test");
        map.put(6, "6. Take a Test");
        map.put(7, "7. Tabulate a Test");
        map.put(8, "8. Grade a Test");
        map.put(9, "9. Quit");

        System.out.println();
        System.out.println("Please choose menu by number");
        for (int i = 1; i <= map.size(); i++) {
            System.out.println(map.get(i));
        }
        ArrayList<String> testKeys = new ArrayList<>(testInTheFolder.keySet());
        ArrayList<Test> testValues = new ArrayList<>(testInTheFolder.values());

        int userChoice = 0;
        String SurveyName;
        String path;
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int optionChoosed = keyboard.nextInt();
        switch (optionChoosed) {
            case 1:
                test.createTest();
                Scanner key = new Scanner(System.in);
                System.out.println("Enter name for file to saved as:");
                SurveyName = key.nextLine();
                test.setMySurveyName(SurveyName);
                path = test.getPathOfFile() + SurveyName;
                saveSurvey(test, path);
                break;
            case 2:
                userChoice = getChosenTest();
                if (userChoice != 0)
                    testValues.get(userChoice - 1).displayTest();
                break;
            case 3:
                File file = getSurveyToLoad(test);
                Test tempSurvey = (Test) loadSurvey(file, test);
                if (!testInTheFolder.containsValue(tempSurvey)) {
                    assert file != null;
                    testInTheFolder.put(file.getName(), tempSurvey);
                }
                break;
            case 4:
                System.out.println("Enter name for file to saved as:");
                Scanner scanner = new Scanner(System.in);
                SurveyName = scanner.nextLine();
                test.setMySurveyName(SurveyName);
                path = test.getPathOfFile() + SurveyName;
                saveSurvey(testValues.get(userChoice - 1), path);
                break;
            case 5:
                if (testInTheFolder.isEmpty()) {
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenTest();
                if (userChoice != 0) {
                    testValues.get(userChoice - 1).modifyTest();
                    path = testValues.get(userChoice - 1).getPathOfFile() + testKeys.get(userChoice - 1);
                    saveSurvey(testValues.get(userChoice - 1), path);
                }
                break;
            case 6:
                if (testInTheFolder.isEmpty()) {
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenTest();
                Survey surveyDuplicatedAfterModifiying = new Survey();
                if (userChoice != 0) {
                    String pathForLoad = test.getPathOfFile() + testKeys.get(userChoice - 1);
                    surveyDuplicatedAfterModifiying = loadTest(new File(pathForLoad), new Test());
                    surveyDuplicatedAfterModifiying.takeSurvey();
                }

                String pathOfFile = test.getPathOfFile() + testKeys.get(userChoice - 1) + "Responses";
                File newFolder = new File(pathOfFile);
                if (!newFolder.exists()) {
                    try {
                        newFolder.mkdir();
                    } catch (SecurityException ignored) {
                    }
                }
                System.out.println("Enter name to save your responses as:");
                Scanner scanner1 = new Scanner(System.in);
                SurveyName = scanner1.nextLine();
                test.setMySurveyName(SurveyName);
                path = pathOfFile + "/" + SurveyName;
                saveSurvey(surveyDuplicatedAfterModifiying, path);
                break;
            case 7:
                if (testInTheFolder.isEmpty()) {
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenTest();
                if (getChosenTest() != 0) {
                    ArrayList<LinkedHashMap<String, Integer>> linkedHashMap = null;
                    try {
                        linkedHashMap = testValues.get(userChoice - 1).tabulateSurvey();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!(linkedHashMap == null)){
                        testValues.get(userChoice - 1).printTabulation(linkedHashMap);
                    }
                }
                break;
            case 8:
                if (test.getPathOfFile().equals("Surveys/")) {
                    handalingTest(test);
                    //exit(0);
                } else {
                    if (testInTheFolder.isEmpty()) {
                        System.out.println("There are no tests loaded. Please load one!");
                        break;
                    }
                    userChoice = getChosenTest();
                    if (userChoice != 0) {
                        grade = testValues.get(userChoice - 1).gradeTest();
                    }
                    if (grade > 0) {

                        System.out.println("Your Grade is " + Integer.toString(grade) + "/"
                                + Integer.toString((testValues.get(userChoice - 1).getQuestions().size()) * 10) + "\n");

                    } else if (grade == 0) {
                        System.out.println("Your Grade is " + Integer.toString(grade) + "/"
                                + Integer.toString((testValues.get(userChoice - 1).getQuestions().size()) * 10) + "\n");
                    }
                }
                break;
            case 9:
                System.out.println("You choosed to exit");
                exit(0);
        }
    }

    public static Survey loadTest(File file, Test test) {
        String pathOfFile = file.toString();
        String SurveyName = file.getName();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathOfFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            test = (Test) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
        }

        return test;
    }

    public static Survey loadSurvey(File file, Survey survey) {
        String pathOfFile = file.toString();
        String SurveyName = file.getName();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathOfFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            survey = (Survey) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
        }

        return survey;
    }


    public static void saveSurvey(Survey survey, String path) {
        if (survey.getQuestions().size() == 0)
        {
            System.out.println("No Survey to save!");
            return;
        }
        try {
            File newFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(survey);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Survey/Test is saved.");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    private static void handalingSurvey(Survey survey){
        ArrayList<Survey> values = new ArrayList<>(surveyIntheFolder.values());
        ArrayList<String> keys = new ArrayList<>(surveyIntheFolder.keySet());
        int userChoice = 0;
        String SurveyName;
        String path;
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"1. Create a new Survey");
        map.put(2,"2. Display a Survey");
        map.put(3,"3. Load a Survey");
        map.put(4,"4. Save a Survey");
        map.put(5,"5. Modify an Existing Survey");
        map.put(6,"6. Take a Survey");
        map.put(7,"7. Tabulate a Survey");
        map.put(8,"8. Quit");
        System.out.println();
        System.out.println("Please choose menu by number");
        for(int i = 1; i <= map.size(); i++){
            System.out.println(map.get(i));
        }
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int optionChoosed = keyboard.nextInt();
        switch (optionChoosed) {
            case 1:
                survey.createSurvey();
                Scanner key = new Scanner(System.in);
                System.out.println("Enter name for file to saved as:");
                SurveyName = key.nextLine();
                survey.setMySurveyName(SurveyName);
                path= survey.getPathOfFile() + SurveyName;
                saveSurvey(survey, path);
                break;
            case 2:
                userChoice = getChosenSurvey();
                if (userChoice != 0)
                    values.get(userChoice-1).displaySurvey();
                break;
            case 3:
                File file = getSurveyToLoad(survey);
                Survey tempSurvey = loadSurvey(file, survey);
                if (!surveyIntheFolder.containsValue(tempSurvey)) {
                    String name = file.getName();
                    surveyIntheFolder.put(name, tempSurvey);
                }
                break;
            case 4:
                System.out.println("Enter name for file to saved as:");
                Scanner scanner = new Scanner(System.in);
                SurveyName = scanner.nextLine();
                survey.setMySurveyName(SurveyName);
                path= survey.getPathOfFile() + SurveyName;
                saveSurvey(values.get(userChoice-1), path);
                break;
            case 5:
                if (surveyIntheFolder.isEmpty()){
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenSurvey();
                if (userChoice != 0) {
                    values.get(userChoice - 1).modifySurvey();
                    path= values.get(userChoice - 1).getPathOfFile() + keys.get(userChoice - 1);
                    saveSurvey(values.get(userChoice - 1), path);
                }
                break;
            case 6:
                if (surveyIntheFolder.isEmpty()){
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenSurvey();
                Survey surveyDuplicatedAfterModifiying = new Survey();
                if (userChoice != 0) {
                    String pathForLoad = survey.getPathOfFile() + keys.get(userChoice-1);
                    surveyDuplicatedAfterModifiying = loadSurvey(new File(pathForLoad), new Survey());
                    surveyDuplicatedAfterModifiying.takeSurvey();
                }

                String pathOfFile= survey.getPathOfFile() + keys.get(userChoice-1) + "Responses";
                File newFolder = new File(pathOfFile);
                if (!newFolder.exists()){
                    try{
                        newFolder.mkdir();
                    }
                    catch(SecurityException ignored){
                    }
                }
                System.out.println("Enter name to save your responses as:");
                Scanner scanner1 = new Scanner(System.in);
                SurveyName = scanner1.nextLine();
                survey.setMySurveyName(SurveyName);
                path= pathOfFile+ "/" + SurveyName;
                saveSurvey(surveyDuplicatedAfterModifiying, path);
                break;
            case 7:
                if (surveyIntheFolder.isEmpty()){
                    System.out.println("There are no surveys loaded. Please load one!");
                    break;
                }
                userChoice = getChosenSurvey();
                if (userChoice != 0) {
                    ArrayList<LinkedHashMap<String, Integer>> linkedHashMap = null;
                    try {
                        linkedHashMap = values.get(userChoice - 1).tabulateSurvey();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!(linkedHashMap == null))
                        values.get(userChoice - 1).printTabulation(linkedHashMap);
                }
                break;
            case 8:
                System.out.println("You choosed to exit");
                exit(0); //Quit
        }
    }
}
