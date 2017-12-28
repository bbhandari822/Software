import java.util.Scanner;

/**
 * Created by Binod Bhandari on 10/26/17.
 */

public class ShortAnswer extends Question {
    private int restrictionOfChoices = 0;

    public boolean gradeTest(Response res, Response rightAnswer) {
        return false;
    }

    public boolean isEssayOrShortAnswer() {return true;}

    public int getRestrictionOfChoices() {
        return restrictionOfChoices;
    }

    public void setRestrictionOfChoices() {
        System.out.print("Enter number of allowed responses: ");
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        this.restrictionOfChoices = keyboard.nextInt();;
    }
}
