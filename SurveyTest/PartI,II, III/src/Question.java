import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

/**
 * This is the base class for all the question type. This class handle all the question prompt. It sets the restriction of choices
 * to display choices. Ask for console prompt.
 */

public class Question implements Serializable {

    private String questionPrompt = "";

    public void setQuestionPrompt(String typeOfQuestion) {
        System.out.println("Enter the prompt for your " + typeOfQuestion );
        Scanner keyboard = new Scanner(System.in);
        this.questionPrompt = keyboard.nextLine();;
    }

    public void setQuestionPrompt() {
        System.out.println("Enter the prompt for your question:");
        Scanner keyboard = new Scanner(System.in);
        this.questionPrompt = keyboard.nextLine();;
    }

    public void modifyQuestion() {

        System.out.println("Do you wish to modify the prompt? ");
        Scanner key = new Scanner(System.in);
        String input = key.next();

        switch (input) {
            case "Yes":
                System.out.println("Enter the prompt for your question:");
                Scanner keyboard = new Scanner(System.in);
                this.questionPrompt = keyboard.nextLine();
                break;
            case "No":
                getChoices();
                modifyChoices();
                break;
            default:
                System.out.println("Invalid Options");
                modifyQuestion();
                break;
        }

    }

    public void modifyChoices() {}

    public String getQuestionPrompt() {
        return this.questionPrompt;
    }

    public void displayQuestions() {
        System.out.println(this.getQuestionPrompt());
    }

    public void displayChoicesOptions() {

    }

    public int getRestrictionOfChoices(){
        return 0;
    }

    public void setRestrictionOfChoices() {
    }

    public void setChoices() {
    }

    public String[] getChoices() {
        return null;
    }

    public Response getResponses() {

        Response response = new Response();
        String[] responseList = new String[this.getRestrictionOfChoices()];
        for (int i = 0; i < this.getRestrictionOfChoices(); i++) {
            String answer = "";
            do {
                System.out.print("Enter Response " + Integer.toString(i + 1) + ": ");
                answer = new Scanner(System.in).nextLine();
            } while (!(contains(this.getChoices(), answer)));

            responseList[i] = answer;
        }
        response.setResponse(responseList);
        return response;
    }

    protected boolean contains(String[] arr, String val) {
        if (arr == null){
            return true;
        }

        for (int i = 0; i < arr.length; i++){

            if (arr[i].equals(val)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEssayOrShortAnswer() {return false;}


}
