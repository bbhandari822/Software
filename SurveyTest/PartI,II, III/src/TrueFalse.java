/**
 * Created by Binod Bhandari on 10/26/17.
 */

public class TrueFalse extends Question {
    private String[] choices = {"True", "False"};
    private int restrictionOfChoices = 1;

    public boolean gradeTest(Response res, Response rightAnswer) {
        if (restrictionOfChoices < 2) {
            if (rightAnswer.getResponse()[0].equals(res.getResponse()[0])) {
                return true;
            }
        }
        return false;
    }

    public int getRestrictionOfChoices() {

        return restrictionOfChoices;
    }

    public String[] getChoices() {

        return this.choices;
    }

    public void displayChoicesOptions() {

        for (String choice : choices) {
            System.out.println(choice);
        }
    }
}
