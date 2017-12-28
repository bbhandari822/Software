import java.util.Scanner;

/**
 * Created by Binod Bhandari on 10/26/17.
 */
public class MutipleChoiceQuestions extends Question {

    protected String[] options;
    protected int restrictionOfChoices;

    public int getRestrictionOfChoices() {

        return restrictionOfChoices;
    }

    public boolean gradeTest(Response res, Response rightAnswer) {
        //Check if the answer is correct or not
        if(restrictionOfChoices < 2){
            if (rightAnswer.getResponse()[0].equals(res.getResponse()[0])){
                return true;
            }
        }else if(restrictionOfChoices < 3){
            if (rightAnswer.getResponse()[0].equals(res.getResponse()[0]) && rightAnswer.getResponse()[1].equals(res.getResponse()[1])){
                return true;
            }
        }else if(restrictionOfChoices < 4){
            if (rightAnswer.getResponse()[0].equals(res.getResponse()[0]) && rightAnswer.getResponse()[1].equals(res.getResponse()[1]) && rightAnswer.getResponse()[2].equals(res.getResponse()[2])){
                return true;
            }
        }else{
            if (rightAnswer.getResponse()[0].equals(res.getResponse()[0]) && rightAnswer.getResponse()[1].equals(res.getResponse()[1]) && rightAnswer.getResponse()[2].equals(res.getResponse()[2]) && rightAnswer.getResponse()[3].equals(res.getResponse()[3])){
                return true;
            }
        }

        return false;
    }

    public void setRestrictionOfChoices() {
        System.out.print("Enter number of allowed responses: ");
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        this.restrictionOfChoices = keyboard.nextInt();
    }

    public String[] getChoices() {
        return options;
    }

    /*
    * It asks for the number of answer options for the question
    * Also sets the number of options that can be chosen as a response
     */
    public void setChoices() {
        System.out.println("Enter number of options: ");
        Scanner keyboard = new Scanner(System.in);
        while(!keyboard.hasNextInt()) {
            System.out.println("Please enter integer only: ");
            keyboard.next();
        }
        int totalNumberOfOptions = keyboard.nextInt();
        this.options = new String[totalNumberOfOptions];
        for (int i = 0; i < totalNumberOfOptions; i++) {
            System.out.println("Enter choice #" + Integer.toString(i + 1));
            Scanner key = new Scanner(System.in);
            String option = key.nextLine();
            this.options[i] = option;
        }
    }

    public void modifyChoices() {

        String repeatProcess;
        do {
            System.out.println("Do you wish to modify the options?");
            Scanner keyboard = new Scanner(System.in);
            String userPromptVerify = keyboard.nextLine();
            if (userPromptVerify.toLowerCase().equals("yes")) {
                System.out.println("Which option would you like to modify?");
                this.displayChoicesOptions();
                Scanner keyboard1 = new Scanner(System.in);
                while (!keyboard1.hasNextInt()) {
                    System.out.println("Please enter integer only: ");
                    keyboard1.next();
                }
                int optionChoice = keyboard1.nextInt();
                System.out.println(this.options[optionChoice - 1]);
                System.out.println("Enter new option:");
                Scanner keyboard2 = new Scanner(System.in);
                String newOption = keyboard2.nextLine();
                this.options[optionChoice - 1] = newOption;
            }
            repeatProcess = userPromptVerify;
        } while (repeatProcess.toLowerCase().equals("yes"));
    }

    public void displayChoicesOptions() {

        for (int i = 0; i < options.length; i++) {
            int ab = i + 1;
            System.out.println(ab + ") " + options[i]);
        }

    }
}
