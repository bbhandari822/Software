import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

public class Test extends Survey {

    protected String filePath = "Tests/";
    private ArrayList<Response> correctAnswers = new ArrayList<>();

    public ArrayList<Response> getCorrectAnswers() {
        return this.correctAnswers;
    }


    public void setCorrectAnswers(Question question) {
        Response correctAnswer = new Response();
        if (!question.isEssayOrShortAnswer()) {
            correctAnswer = question.getResponses();
        } else {
            String[] nullString = {};
            correctAnswer.setResponse(nullString);
        }
       this.correctAnswers.add(correctAnswer);
    }

    public void setCorrectAnswersAfterModifying(Question question) {
        Response correctAnswer = new Response();
        if (!question.isEssayOrShortAnswer()) {
            correctAnswer = question.getResponses();
        } else {
            String[] nullString = {};
            correctAnswer.setResponse(nullString);
        }
        this.correctAnswers.set(getCorrectAnswers().size()-1,correctAnswer);
    }

    public void displayCorrectAnswersTest(int i) {
        System.out.println("The correct answer is:");
        Response currentResponse = this.correctAnswers.get(i);
        currentResponse.displayResponse();


    }

    public void modifyCorrectAnswers(Question question) {
        System.out.println("Do you wish to modify the correct Answer?");
        String answerYesNo = new Scanner(System.in).nextLine();
        if (answerYesNo.toLowerCase().equals("yes")) {
            setCorrectAnswersAfterModifying(question);
        }
    }

    public String getPathOfFile() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public int gradeTest() {
        String filepath = this.getPathOfFile() + this.getMySurveyName() + "Responses/";
        File folder = new File(filepath);
        if(folder.list().length == 0) {
            System.out.println("No surveys have been taken!");
            return 0;
        }
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(Integer.toString(i+1)+ ") " + listOfFiles[i].getName());
            }
        }
        System.out.println("Enter file to grade");
        int number;
        do{
            System.out.println("Please enter valid number: ");
            Scanner scanner = new Scanner(System.in);
            number = scanner.nextInt();
        } while (!(listOfFiles[number - 1].isFile()));
        Survey tempSurvey = Main.loadSurvey(listOfFiles[number - 1], new Survey());
        int grade = 0;
            for (int i = 0; i < getCorrectAnswers().size(); i++) {
                if (getCorrectAnswers().get(i).compare(tempSurvey.getResponses().get(i))) {
                    grade = grade+10;
                }
            }

        return grade;
    }

    public Survey createTest() {

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
            setCorrectAnswers(question);
            this.questions.add(question);

        }
        return this;
    }

    public void modifyTest() {

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
            modifyCorrectAnswers(question);
            System.out.println("Do you wish to modify another question?");
            repeatProcess = new Scanner(System.in).nextLine();
        } while (repeatProcess.toLowerCase().equals("yes"));
    }

    public void displayTest() {

        IntStream.range(0, this.questions.size()).forEach(i -> {
            this.questions.get(i).displayQuestions();
            this.questions.get(i).displayChoicesOptions();
            displayCorrectAnswersTest(i);
            System.out.println();
        });
    }

}