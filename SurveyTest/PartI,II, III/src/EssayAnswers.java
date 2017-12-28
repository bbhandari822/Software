import java.util.Scanner;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

public class EssayAnswers extends Question {

    protected int restrictionOfChoices = 1;

    public boolean gradeTest(Response res, Response rightAnswer) {

        return false;
    }

    public boolean isEssayOrShortAnswer() {
        return true;
    }


    public Response getResponses() {

        Response response = new Response();
        String [] responseFromUser = new String[this.restrictionOfChoices];
        System.out.println("Enter Response");
        Scanner keyboard = new Scanner(System.in);
        String answer = keyboard.nextLine();
        responseFromUser[0] = answer;
        response.setResponse(responseFromUser);
        return response;
    }

    public int getRestrictionOfChoices() {

        return restrictionOfChoices;
    }

}
