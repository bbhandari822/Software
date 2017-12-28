import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

/**
 * This survey class handle most of the work. It load, save, load, serialize questions, response. It also have derived class: Test.java
 * the only difference it that test class have option to choose right answer while survey don't. I used hashmap to store data, it is
 * one of the fastest data structure for lookups.
 */

public class Survey implements Serializable,Cloneable {

    public ArrayList<Question> questions = new ArrayList<>();
    protected ArrayList<Response> responses = new ArrayList<>();

    private String filePath = "Surveys/"; //File path for serializing

    protected String mySurveyName = "";

    protected int numOfQuestions = 0;

    public Survey createSurvey() {

        System.out.println();
        System.out.println("Please enter number of questions you want to create from the list below");
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        this.numOfQuestions = keyboard.nextInt();
        for (int i = 0; i < numOfQuestions; i++) {
            Question question = addQuestionsInTheSurvey();
            this.questions.add(question);

        }
        return this;
    }

    public Question addQuestionsInTheSurvey() {

        Question questions = new Question();
        System.out.println();
        System.out.println("Here are the question type, Please choose one number from the list.");
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "1) Add a new Multiple Choice Question");
        hashMap.put(2, "2) Add a new T/F Question");
        hashMap.put(3, "3) Add a new Essay Question");
        hashMap.put(4, "4) Add a new Short Answer Question");
        hashMap.put(5, "5) Add a new Ranking Question");
        hashMap.put(6, "6) Add a new Matching Questions");

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Multiple Choice Question");
        map.put(2, "T/F Question");
        map.put(3, "Essay Question");
        map.put(4, "Short Answer Question");
        map.put(5, "Ranking Question");
        map.put(6, "Matching Questions");

        for (int i = 1; i <= hashMap.size(); i++) {
            System.out.println(hashMap.get(i));
        }
        System.out.println();
        System.out.println("Please choose what kind of questions you want to add: ");
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int typeOfQuestionChoosed = keyboard.nextInt();
        if (typeOfQuestionChoosed == 1) {
            questions = new MutipleChoiceQuestions();
        } else if (typeOfQuestionChoosed == 2) {
            questions = new TrueFalse();
        } else if (typeOfQuestionChoosed == 3) {
            questions = new EssayAnswers();
        } else if (typeOfQuestionChoosed == 4) {
            questions = new ShortAnswer();
        } else if (typeOfQuestionChoosed == 5) {
            questions = new Ranking();
        } else if (typeOfQuestionChoosed == 6) {
            questions = new Matching();
        } else {
            System.out.println("Wrong Choice, Please try again");
            addQuestionsInTheSurvey();
        }
        questions.setQuestionPrompt(map.get(typeOfQuestionChoosed));
        questions.setChoices();
        questions.setRestrictionOfChoices();
        return questions;
    }


    public void takeSurvey()
    {
        for (int i = 0; i < this.numOfQuestions; i++)
        {
            Question question = this.questions.get(i);
            question.displayQuestions();
            question.displayChoicesOptions();
            Response response = question.getResponses();
            this.responses.add(response);
        }
    }

    public void modifySurvey()
    {
        String repeatProcess;
        do {
            System.out.println("What question do you wish to modify?");
            for (int i = 0; i < this.numOfQuestions; i++) {
                int a = i+1;
                System.out.print(a + ":- ");
                this.questions.get(i).displayQuestions();
            }
            Scanner keyboard = new Scanner(System.in);
            while(!keyboard.hasNextInt()) {
                System.out.println("Please enter integer only: ");
                keyboard.next();
            }
            int choiceOfQuestion = keyboard.nextInt();
            Question question = this.getQuestions().get(choiceOfQuestion - 1);
            System.out.println("Do you wish to modify the prompt?");
            String userpro = new Scanner(System.in).nextLine();
            if (userpro.toLowerCase().equals("yes")) {
                question.setQuestionPrompt();
            }
            question.modifyChoices();
            System.out.println("Do you wish to modify another question?");
            repeatProcess = new Scanner(System.in).nextLine();
        } while (repeatProcess.toLowerCase().equals("yes"));
    }

    public void displaySurvey() {

        IntStream.range(0, this.questions.size()).forEach(i -> {
            this.questions.get(i).displayQuestions();
            this.questions.get(i).displayChoicesOptions();
            System.out.println();
        });
    }


    public ArrayList<LinkedHashMap<String, Integer>> tabulateSurvey() throws IOException {
        ArrayList<ArrayList<Response>> totalSurveysIntheFolder = new ArrayList<>();
        ArrayList<LinkedHashMap<String, Integer>>  linkedHashMaps = new ArrayList<>();
        String filepath = this.getPathOfFile() + this.getMySurveyName() + "Responses/";
        File folder = new File(filepath);
        if(!folder.exists() || folder.list().length == 0) {
            System.out.println("No surveys have been taken!");
            return null;
        }
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                Survey tempSurvey = Main.loadSurvey(listOfFile, new Survey());
                totalSurveysIntheFolder.add(tempSurvey.getResponses());
            }
        }

        for (int i = 0; i < this.numOfQuestions; i++) {

            LinkedHashMap<String, Integer> maps = new LinkedHashMap<>();
            linkedHashMaps.add(maps);
        }

        for (ArrayList<Response> responsesFromCurrentSurvey : totalSurveysIntheFolder) {
            IntStream.range(0, this.numOfQuestions).forEach(j -> {
                String[] stringsFromCurrentResponse = responsesFromCurrentSurvey.get(j).getResponse();
                for (String aStringsFromCurrentResponse : stringsFromCurrentResponse) {
                    if (linkedHashMaps.get(j).isEmpty()) {
                        linkedHashMaps.get(j).put(aStringsFromCurrentResponse, 1);
                    } else if (linkedHashMaps.get(j).containsKey(aStringsFromCurrentResponse)) {
                        linkedHashMaps.get(j).put(aStringsFromCurrentResponse,
                                linkedHashMaps.get(j).get(aStringsFromCurrentResponse) + 1);
                    } else {
                        linkedHashMaps.get(j).put(aStringsFromCurrentResponse, 1);
                    }
                }
            });
        }

        return linkedHashMaps;
    }

    public void printTabulation(ArrayList<LinkedHashMap<String, Integer>> mapForQuestion) {

        int i = 0;
        while (i < this.numOfQuestions) {
            System.out.println(this.questions.get(i).getQuestionPrompt());
            ArrayList<Integer> values = new ArrayList<>(mapForQuestion.get(i).values());
            ArrayList<String> keys = new ArrayList<>(mapForQuestion.get(i).keySet());
            IntStream.range(0, values.size()).mapToObj(j -> Integer.toString(values.get(j)) + "\t" + keys.get(j)).forEach(System.out::println);
            System.out.println();
            i++;
        }

    }


    public ArrayList<Response> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
    }

    public ArrayList<Question> getQuestions() {return questions;}

    public String getPathOfFile() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMySurveyName() {

        return mySurveyName;
    }

    public void setMySurveyName(String mySurveyName) {

        this.mySurveyName = mySurveyName;
    }

}
